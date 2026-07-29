package com.cream.user.aspect;

import com.cream.base.spel.entity.SpELEvaluationContext;
import com.cream.base.spel.entity.SpELObject;
import com.cream.base.spel.evaluator.SpELEvaluator;
import com.cream.flowable.annotion.ClaimTask;
import com.cream.flowable.annotion.CompleteTask;
import com.cream.flowable.annotion.StartProcess;
import com.cream.flowable.aspect.AbstractWorkflowService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

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
     * 日志SpEL解析器
     */
    private final SpELEvaluator evaluator = new SpELEvaluator();

    /**
     * 实现获取当前用户
     */
    @Override
    protected String resolveCurrentUser() {
        // todo 等 security 模块实现后，再来实现
        return "张三";
    }

    /**
     * 处理 @StartProcess
     */
    @Around("@annotation(com.cream.flowable.annotion.StartProcess)")
    public Object aroundStartProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(joinPoint.getTarget());

        // SpEL解析
        StartProcess annotation = method.getAnnotation(StartProcess.class);
        SpELObject spELObject = new SpELObject(method, args, targetClass);
        SpELEvaluationContext context = new SpELEvaluationContext(spELObject, evaluator.getDiscoverer());
        String processKey = evaluator.parse(annotation.processKey(), context).toString();
        String businessKey = evaluator.parse(annotation.businessKey(), context).toString();

        Map<String, Object> variables = extractVariables(joinPoint);
        doStartProcess(processKey, businessKey, variables);

        return result;
    }

    /**
     * 处理 @CompleteTask
     */
    @Around("@annotation(com.cream.flowable.annotion.CompleteTask)")
    public Object aroundCompleteTask(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(joinPoint.getTarget());

        // SpEL解析
        CompleteTask annotation = method.getAnnotation(CompleteTask.class);
        SpELObject spELObject = new SpELObject(method, args, targetClass);
        SpELEvaluationContext context = new SpELEvaluationContext(spELObject, evaluator.getDiscoverer());
        String assignee = evaluator.parse(annotation.assignee(), context).toString();
        String businessKey = evaluator.parse(annotation.businessKey(), context).toString();

        Map<String, Object> variables = extractVariables(joinPoint);

        doCompleteTask(businessKey, assignee, variables);

        return joinPoint.proceed();
    }

    /**
     * 处理 @ClaimTask
     */
    @Around("@annotation(claimTask)")
    public Object aroundClaimTask(ProceedingJoinPoint joinPoint,
                                  ClaimTask claimTask) throws Throwable {
        String businessKey = resolveBusinessKey(claimTask.businessKey(), joinPoint);
        String assignee = claimTask.assignee().isEmpty()
                ? resolveCurrentUser()
                : claimTask.assignee();

        doClaimTask(businessKey, assignee);

        return joinPoint.proceed();
    }

    private String resolveBusinessKey(String expression, ProceedingJoinPoint jp) {
        if (StringUtils.isNotBlank(expression)) {
            return expression;
        }
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Map) {
                Object bk = ((Map<?, ?>) arg).get("businessKey");
                if (bk != null) {
                    return bk.toString();
                }
            }
        }
        return "";
    }

}
