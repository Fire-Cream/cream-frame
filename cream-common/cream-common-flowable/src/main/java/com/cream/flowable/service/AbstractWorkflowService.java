package com.cream.flowable.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 工作流抽象服务
 *
 * @author Cream
 * @since 2026-07-12 18:54
 */
@Slf4j
public abstract class AbstractWorkflowService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Resource
    private IdentityService identityService;

    /**
     * 启动流程实例
     *
     * @param processKey 流程定义KEY
     * @param businessKey 业务KEY
     * @param variables 流程变量
     * @param initiator 流程发起人
     * @return 流程实例
     */
    public ProcessInstance startProcess(String processKey, String businessKey, Map<String, Object> variables, String initiator) {
        log.info("启动流程: processKey={}, businessKey={}, variables={}", processKey, businessKey, variables);
        if (StringUtils.isEmpty(processKey)) {
            identityService.setAuthenticatedUserId(initiator);
        }
        if (variables == null || variables.isEmpty()) {
            return runtimeService.startProcessInstanceByKey(processKey, businessKey);
        }
        return runtimeService.startProcessInstanceByKey(processKey, businessKey, variables);
    }

    /**
     * 根据业务KEY查找正在运行的流程实例
     *
     * @param businessKey 业务KEY
     * @return 流程实例，未找到返回null
     */
    public ProcessInstance getProcessInstanceByBusinessKey(String businessKey) {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .list();
        return list.isEmpty() ? null : list.getFirst();
    }

    /**
     * 根据业务KEY查找当前活跃任务列表
     *
     * @param businessKey 业务KEY
     * @return 任务列表
     */
    public List<Task> getTasksByBusinessKey(String businessKey) {
        ProcessInstance instance = getProcessInstanceByBusinessKey(businessKey);
        if (instance == null) {
            log.warn("[getTasksByBusinessKey]未找到业务KEY对应的流程实例: {}", businessKey);
            return Collections.emptyList();
        }
        return taskService.createTaskQuery()
                .processInstanceId(instance.getId())
                .list();
    }

    /**
     * 根据业务KEY和审批人查找当前任务
     *
     * @param businessKey 业务KEY
     * @param assignee 审批人
     * @return 任务，未找到返回null
     */
    public Task getTaskByBusinessKeyAndAssignee(String businessKey, String assignee) {
        ProcessInstance instance = getProcessInstanceByBusinessKey(businessKey);
        if (instance == null) {
            log.warn("[getTaskByBusinessKeyAndAssignee]未找到业务KEY对应的流程实例: {}", businessKey);
            return null;
        }
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(instance.getId())
                .taskAssignee(assignee)
                .list();
        return tasks.isEmpty() ? null : tasks.getFirst();
    }

    /**
     * 完成任务
     *
     * @param taskId 任务ID
     * @param variables 任务变量
     */
    public void completeTask(String taskId, Map<String, Object> variables) {
        log.info("完成任务: taskId={}, variables={}", taskId, variables);
        if (variables == null || variables.isEmpty()) {
            taskService.complete(taskId);
        } else {
            taskService.complete(taskId, variables);
        }
    }

    /**
     * 根据业务KEY完成当前任务
     * <p>
     * 如果指定了assignee则精确定位任务，否则取第一个活跃任务。
     * 同时添加审批意见。
     *
     * @param businessKey 业务KEY
     * @param assignee 审批人 (可为空)
     * @param variables 任务变量 (可为空)
     * @param comment 审批意见 (可为空)
     */
    public void completeTaskByBusinessKey(String businessKey, String assignee,
                                          Map<String, Object> variables, String comment) {
        Task task;
        if (assignee != null && !assignee.isEmpty()) {
            task = getTaskByBusinessKeyAndAssignee(businessKey, assignee);
        } else {
            List<Task> tasks = getTasksByBusinessKey(businessKey);
            task = tasks.isEmpty() ? null : tasks.getFirst();
        }

        if (task == null) {
            throw new RuntimeException("未找到可完成的任务, businessKey=" + businessKey
                    + ", assignee=" + assignee);
        }

        // 添加审批意见
        if (comment != null && !comment.isEmpty()) {
            taskService.addComment(task.getId(), task.getProcessInstanceId(), comment);
        }

        completeTask(task.getId(), variables);
    }

    /**
     * 删除流程实例
     *
     * @param processInstanceId 流程实例ID
     * @param reason 删除原因
     */
    public void deleteProcess(String processInstanceId, String reason) {
        log.info("删除流程实例: processInstanceId={}, reason={}", processInstanceId, reason);
        runtimeService.deleteProcessInstance(processInstanceId, reason);
    }

    /**
     * 根据业务KEY删除流程实例
     *
     * @param businessKey 业务KEY
     * @param reason 删除原因
     */
    public void deleteProcessByBusinessKey(String businessKey, String reason) {
        ProcessInstance instance = getProcessInstanceByBusinessKey(businessKey);
        if (instance != null) {
            deleteProcess(instance.getId(), reason);
        } else {
            log.warn("[deleteProcessByBusinessKey]未找到业务KEY对应的流程实例: {}", businessKey);
        }
    }

    /**
     * 获取流程实例历史
     *
     * @param processInstanceId 流程实例ID
     * @return 历史流程实例
     */
    public HistoricProcessInstance getHistoricProcessInstance(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
    }

    /**
     * 查询用户待办任务
     *
     * @param assignee 用户ID
     * @return 任务列表
     */
    public List<Task> getTodoTasks(String assignee) {
        return taskService.createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskCreateTime()
                .desc()
                .list();
    }

    /**
     * 查询用户待办任务 (含候选人/组)
     *
     * @param userId 用户ID
     * @return 任务列表
     */
    public List<Task> getTodoTasksWithCandidate(String userId) {
        return taskService.createTaskQuery()
                .taskCandidateUser(userId)
                .orderByTaskCreateTime()
                .desc()
                .list();
    }

    /**
     * 认领任务
     *
     * @param taskId 任务ID
     * @param userId 用户ID
     */
    public void claimTask(String taskId, String userId) {
        log.info("认领任务: taskId={}, userId={}", taskId, userId);
        taskService.claim(taskId, userId);
    }

    /**
     * 转派任务
     *
     * @param taskId 任务ID
     * @param userId 目标用户ID
     */
    public void delegateTask(String taskId, String userId) {
        log.info("转派任务: taskId={}, targetUserId={}", taskId, userId);
        taskService.delegateTask(taskId, userId);
    }

}
