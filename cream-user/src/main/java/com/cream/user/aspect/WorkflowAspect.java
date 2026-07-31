package com.cream.user.aspect;

import com.cream.base.spel.entity.SpELEvaluationContext;
import com.cream.base.spel.utils.SpELUtils;
import com.cream.flowable.annotion.CompleteTask;
import com.cream.flowable.annotion.StartProcess;
import com.cream.flowable.service.AbstractWorkflowService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 工作流切面实现
 *
 * @author Cream
 * @since 2026-07-12 19:15
 */
@Slf4j
@Aspect
@Component
public class WorkflowAspect extends AbstractWorkflowService {

    /**
     * 处理 @StartProcess
     */
    @Around("@annotation(startProcess)")
    public Object aroundStartProcess(ProceedingJoinPoint joinPoint, StartProcess startProcess) throws Throwable {
        Object result = joinPoint.proceed();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // SpEL解析
            SpELEvaluationContext context = SpELUtils.build(signature.getMethod(), joinPoint.getArgs());
            String processKey = SpELUtils.parse(startProcess.processKey(), context);
            String businessKey = SpELUtils.parse(startProcess.businessKey(), context);
            HashMap<String, Object> variables = SpELUtils.parse(startProcess.variables(), context);
            String initiator = SpELUtils.parse(startProcess.initiator(), context);
            // 开启流程
            ProcessInstance processInstance = startProcess(processKey, businessKey, variables, initiator);
            log.info("流程启动成功: processKey={}, businessKey={}", processKey, businessKey);
            // 将流程ID绑定到业务实体
        }catch (Exception e) {
            log.error("[startProcess]:流程启动失败", e);
            // 注解操作失败不影响原方法的返回值，但抛出异常让调用方感知
            throw new RuntimeException("流程启动失败: " + e.getMessage(), e);
        }
        return result;
    }

    /**
     * 处理 @CompleteTask
     */
    @Around("@annotation(completeTask)")
    public Object aroundCompleteTask(ProceedingJoinPoint joinPoint, CompleteTask completeTask) throws Throwable {
        // 执行原方法
        Object proceed = joinPoint.proceed();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // SpEL解析
            SpELEvaluationContext context = SpELUtils.build(signature.getMethod(), joinPoint.getArgs());
            String businessKey = SpELUtils.parse(completeTask.businessKey(), context);
            String assignee = SpELUtils.parse(completeTask.assignee(), context);
            HashMap<String, Object> variables = SpELUtils.parse(completeTask.variables(), context);
            Integer approved = SpELUtils.parse(completeTask.approved(), context);
            String comment = SpELUtils.parse(completeTask.comment(), context);
            // 注入审批结果变量
            if (variables == null) {
                variables = new HashMap<>();
            }
            variables.put("approved", approved);
            // 完成任务
            completeTaskByBusinessKey(businessKey, assignee, variables, comment);
            log.info("任务完成成功: businessKey={}, assignee={}, approved={}", businessKey, assignee, approved);
            // 记录到审核记录里面去
        } catch (Exception e) {
            log.error("[completeTask]:任务完成失败", e);
            throw new RuntimeException("任务完成失败: " + e.getMessage(), e);
        }
        return proceed;
    }

}
