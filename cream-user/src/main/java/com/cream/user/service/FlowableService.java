package com.cream.user.service;

import java.util.HashMap;

/**
 * 工作流接口
 *
 * @author Cream
 * @since 2026-07-12 19:49
 */
public interface FlowableService {

    /**
     * 开启流程
     *
     * @param processKey 流程ID
     * @param businessKey 业务ID
     * @param variables 流程变量
     * @author Cream
     * @since 2026-07-13 20:30
     */
    void startProcess(String processKey, String businessKey, HashMap<String, Object> variables);

    /**
     * 执行流程节点
     *
     * @param assignee 当前审批人
     * @param businessKey 业务ID
     * @param variables 流程变量
     * @author Cream
     * @since 2026-07-13 20:33
     */
    void completeTask(String assignee, String businessKey, HashMap<String, Object> variables);

}
