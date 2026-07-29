package com.cream.user;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Flowable 测试类
 *
 * @author Cream
 * @since 2026-07-11 22:33
 */
@Slf4j
@SpringBootTest
public class FlowableTest {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Resource
    private IdentityService identityService;

    /**
     * 部署流程实例
     *
     * @author Cream
     * @since 2026-07-12 11:51
     */
    @Test
    public void deployDefinition() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("processes/holiday-request.bpmn20.xml")
                .name("请假流程")
                .deploy();
        assertNotNull(deploy.getId());
        log.info("流程实例部署成功:{}", deploy.getId());
    }

    /**
     * 查询流程实例
     *
     * @author Cream
     * @since 2026-07-12 12:42
     */
    @Test
    public void queryLastDefinitions() {
        List<Map<String, Object>> list = repositoryService.createProcessDefinitionQuery()
                .latestVersion()
                .orderByProcessDefinitionName().asc()
                .list()
                .stream()
                .map(def -> {
                    Map<String, Object> m = new LinkedHashMap<>();
                    m.put("id", def.getId());
                    m.put("key", def.getKey());
                    m.put("name", def.getName());
                    m.put("version", def.getVersion());
                    m.put("deploymentId", def.getDeploymentId());
                    m.put("suspended", def.isSuspended());
                    return m;
                })
                .toList();
        assertNotEquals(0, list.size());
        log.info("流程实例查询成功:{}", list);
    }

    /**
     * 删除流程实例 <br>
     * 如果流程开始了，不允许删除
     *
     * @author Cream
     * @since 2026-07-12 12:48
     */
    @Test
    public void deleteDefinitions() {
        repositoryService.createProcessDefinitionQuery()
                .latestVersion()
                .orderByProcessDefinitionName().asc()
                .list()
                .stream()
                .map(def -> {
                    Map<String, Object> m = new LinkedHashMap<>();
                    m.put("id", def.getId());
                    m.put("key", def.getKey());
                    m.put("name", def.getName());
                    m.put("version", def.getVersion());
                    m.put("deploymentId", def.getDeploymentId());
                    m.put("suspended", def.isSuspended());
                    return m;
                })
                .toList()
                .forEach(
                        item -> repositoryService.deleteDeployment((String) item.get("deploymentId"))
                );
        log.info("流程实例删除成功");
    }

    /**
     * 启动流程实例
     *
     * @author Cream
     * @since 2026-07-12 13:31
     */
    @Test
    public void runProcess() {
        // 1. 构建流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("employee", "张三");
        variables.put("dayOfHolidays", 3);
        variables.put("description", "年假");
        ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKey("holidayRequest", variables);
        System.out.println("holidayRequest.getProcessDefinitionId() = " + holidayRequest.getProcessDefinitionId());
        System.out.println("holidayRequest.getActivityId() =" + holidayRequest.getActivityId());
        System.out.println("holidayRequest.getId() =" + holidayRequest.getId());
    }

    /**
     * 查询任务
     *
     * @author Cream
     * @since 2026-07-12 14:00
     */
    @Test
    public void queryTask() {
        List<Task> list = taskService.createTaskQuery()
                // 指定查询的编号
                .processDefinitionKey("holidayRequest")
                // 查询任务的处理人
                .taskAssignee("zhangsan")
                .list();

        for (Task task : list) {
            System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
            System.out.println("task.getName() = " + task.getName());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("task.getDescription() = " + task.getDescription());
            System.out.println("task.getId() = " + task.getId());
        }
    }

    /**
     * 完成当前流程
     *
     * @author Cream
     * @since 2026-07-12 14:18
     */
    @Test
    public void completeTask() {
        List<Task> list = taskService.createTaskQuery()
                // 指定查询的编号
                .processDefinitionKey("holidayRequest")
                // 查询任务的处理人
                .taskAssignee("zhangsan")
                .processInstanceBusinessKey("businessKey")
                .list();

        for (Task task : list) {
            // 创建流程变量
            Map<String, Object> variables = new HashMap<>();
            variables.put("approved", false);
            // 完成任务
            taskService.complete(task.getId(), variables);
        }

    }

    /**
     * 获取流程任务的历史数据(乱序)
     *
     * @author Cream
     * @since 2026-07-12 14:28
     */
    @Test
    public void queryHistory() {
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processDefinitionId("holidayRequest:2:c209868b-7db6-11f1-940b-00155d0bb800")
                .finished()
                // 指定排序字段
                .orderByHistoricActivityInstanceId()
                // 指定顺序
                .asc()
                .list();
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println(
                    historicActivityInstance.getActivityName() + ":" +
                            historicActivityInstance.getAssignee() + "--" +
                            historicActivityInstance.getActivityId() + ":" +
                            historicActivityInstance.getDurationInMillis()
            );
        }
    }

}
