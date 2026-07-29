package com.cream.user.service.impl;

import com.cream.flowable.annotion.CompleteTask;
import com.cream.flowable.annotion.StartProcess;
import com.cream.user.service.FlowableService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 工作流接口实现类
 *
 * @author Cream
 * @since 2026-07-12 19:49
 */
@Service
public class FlowableServiceImpl implements FlowableService {

    @Override
    @StartProcess(processKey = "#{#processKey}", businessKey = "#{#businessKey}")
    public void startProcess(String processKey, String businessKey, HashMap<String, Object> variables) {
        System.out.println("业务[" + businessKey + "]:逻辑执行完毕，流程已自动启动");
    }

    @Override
    @CompleteTask(assignee = "#{#assignee}", businessKey = "#{#businessKey}")
    public void completeTask(String assignee, String businessKey, HashMap<String, Object> variables) {
        System.out.println("部门领导[" + assignee + "]已审批");
    }

}
