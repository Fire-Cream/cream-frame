package com.cream.flowable.aspect;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流抽象服务 <br>
 * 需要子类继承并实现
 *
 * @author Cream
 * @since 2026-07-12 18:54
 */
@Slf4j
public abstract class AbstractWorkflowService {

    @Resource
    protected RuntimeService runtimeService;

    @Resource
    protected TaskService taskService;

    /**
     * 子类实现：获取当前用户
     */
    protected abstract String resolveCurrentUser();

    /**
     * 启动流程
     */
    protected void doStartProcess(String processKey, String businessKey,
                                  Map<String, Object> variables) {
        if (StringUtils.isNotBlank(businessKey)) {
            variables.put("businessKey", businessKey);
        }
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                processKey, businessKey, variables);
        log.info("流程已启动: instanceId={}, businessKey={}",
                instance.getId(), businessKey);
    }

    /**
     * 完成任务
     */
    protected void doCompleteTask(String businessKey, String assignee,
                                  Map<String, Object> variables) {
        Task task = findTask(businessKey, assignee);
        if (task == null) {
            throw new RuntimeException("没有找到待办任务, businessKey=" + businessKey);
        }
        log.info("完成任务: taskId={}, taskName={}", task.getId(), task.getName());
        taskService.complete(task.getId(), variables);
    }

    /**
     * 签收任务
     */
    protected void doClaimTask(String businessKey, String assignee) {
        Task task = findTask(businessKey);
        if (task == null) {
            throw new RuntimeException("没有可签收的任务");
        }
        taskService.claim(task.getId(), assignee);
        log.info("任务已签收: taskId={}, assignee={}", task.getId(), assignee);
    }

    /**
     * 查询待办
     */
    protected List<Task> doQueryTodoTasks(String assignee) {
        return taskService.createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskCreateTime().desc()
                .list();
    }

    // ==================== 工具方法 ====================

    protected Task findTask(String businessKey) {
        if (StringUtils.isNotBlank(businessKey)) {
            ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                    .processInstanceBusinessKey(businessKey)
                    .singleResult();
            if (instance != null) {
                return taskService.createTaskQuery()
                        .processInstanceId(instance.getId())
                        .singleResult();
            }
        }
        return taskService.createTaskQuery()
                .taskAssignee(resolveCurrentUser())
                .singleResult();
    }

    protected Task findTask(String businessKey, String assignee) {
        if (StringUtils.isNotBlank(businessKey)) {
            ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                    .processInstanceBusinessKey(businessKey)
                    .singleResult();
            if (instance != null) {
                return taskService.createTaskQuery()
                        .processInstanceId(instance.getId())
                        .taskAssignee(assignee)
                        .singleResult();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> extractVariables(ProceedingJoinPoint joinPoint) {
        Map<String, Object> variables = new HashMap<>();
        try {
            MethodSignature ms = (MethodSignature) joinPoint.getSignature();
            String[] parameterNames = ms.getParameterNames();
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) {
                    continue;
                }
                if (args[i] instanceof Map) {
                    variables.putAll((Map<String, Object>) args[i]);
                } else {
                    variables.put(parameterNames[i], args[i]);
                }
            }
        } catch (Exception e) {
            log.warn("提取流程变量失败", e);
        }
        return variables;
    }

}
