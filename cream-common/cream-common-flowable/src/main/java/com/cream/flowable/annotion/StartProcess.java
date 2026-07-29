package com.cream.flowable.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动流程
 *
 * @author Cream
 * @since 2026-07-12 18:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StartProcess {

    // 流程定义KEY
    String processKey() default "#{#processKey}";

    // 业务KEY，支持 SpEL
    String businessKey() default "#{#businessKey}";

}
