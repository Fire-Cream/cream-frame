package com.cream.flowable.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 完成任务
 *
 * @author Cream
 * @since 2026-07-12 18:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CompleteTask {

    /**
     * 业务KEY (用于定位流程实例)
     */
    String businessKey() default "#{#businessKey}";

    /**
     * 当前审批人 (用于定位当前任务)
     */
    String assignee() default "#{#assignee}";

    /**
     * 任务变量 (一般是Map)
     */
    String variables() default "#{#variables}";

    /**
     * 审批结果
     */
    String approved() default "#{#approved}";

    /**
     * 审批意见
     */
    String comment() default "#{#comment}";

}
