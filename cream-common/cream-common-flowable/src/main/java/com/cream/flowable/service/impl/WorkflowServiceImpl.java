package com.cream.flowable.service.impl;

import com.cream.flowable.annotion.CompleteTask;
import com.cream.flowable.annotion.StartProcess;
import com.cream.flowable.service.WorkflowService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 工作流接口实现类
 *
 * @author Cream
 * @since 2026-07-12 19:49
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Override
    @StartProcess
    public void startProcess(String processKey, String businessKey, HashMap<String, Object> variables, String initiator) {

    }

    @Override
    @CompleteTask
    public void completeTask(String businessKey, String assignee, HashMap<String, Object> variables, Integer approved, String comment) {

    }

}
