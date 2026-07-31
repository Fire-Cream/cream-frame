package com.cream.flowable.service;

import java.util.HashMap;

/**
 * 工作流接口
 *
 * @author Cream
 * @since 2026-07-12 19:49
 */
public interface WorkflowService {

    /**
     * 开启流程
     *
     * @param processKey  流程定义KEY
     * @param businessKey 流程定义KEY
     * @param variables   流程变量
     * @param initiator   流程发起人
     * @author Cream
     * @since 2026-07-13 20:30
     */
    void startProcess(String processKey, String businessKey, HashMap<String, Object> variables, String initiator);

    /**
     * 执行流程节点
     *
     * @param businessKey 流程定义KEY
     * @param assignee    当前审批人
     * @param variables   流程变量
     * @param approved    审批结果
     * @param comment   审批意见
     * @author Cream
     * @since 2026-07-13 20:33
     */
    void completeTask(String businessKey, String assignee, HashMap<String, Object> variables, Integer approved, String comment);

}
