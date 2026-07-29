package com.cream.mpj.annotation;

import com.cream.mpj.enums.QueryTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询注解 <br>
 * 跟工具类联动，自动生成查询条件
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
