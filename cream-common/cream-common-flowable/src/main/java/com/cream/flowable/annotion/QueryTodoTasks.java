package com.cream.flowable.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询待办
 *
 * @author Cream
 * @since 2026-07-12 18:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryTodoTasks {

    // 处理人，支持SpEL
    String assignee() default "#assignee";

}