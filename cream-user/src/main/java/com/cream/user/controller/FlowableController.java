package com.cream.user.controller;

import com.cream.flowable.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * flowable测试类
 *
 * @author Cream
 * @since 2026-07-12 19:48
 */
@RestController("/flowable")
@RequiredArgsConstructor
public class FlowableController {

    private final WorkflowService workflowService;

    @GetMapping("/startProcess")
    public void startProcess() {
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("assignee", "张三");
        variables.put("dayOfHolidays", 3);
        variables.put("description", "年假");
        workflowService.startProcess("holidayRequest","114514", variables, "小米");
    }

    @GetMapping("/completeTask")
    public void completeTask() {
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("assignee", "李四");
        workflowService.completeTask("张三", "114514", variables, 0, "同意");
    }

}
