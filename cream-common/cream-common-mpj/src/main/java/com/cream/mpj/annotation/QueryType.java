package com.cream.mpj.annotation;

import com.cream.mpj.enums.QueryTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @author Cream
 * @since 2026-06-09 22:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface QueryType {

    /**
     * 查询类型
     */
    QueryTypeEnum value() default QueryTypeEnum.EQ;

    /**
     * 属性名称
     */
    String filedName() default "";
}
