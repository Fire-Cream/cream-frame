package com.cream.flowable.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 签收任务
 *
 * @author Cream
 * @since 2026-07-12 18:52
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClaimTask {

    // 处理人，支持SpEL
    String assignee() default "#{assignee}";

    // 根据业务KEY找当前任务
    String businessKey() default "";

}
