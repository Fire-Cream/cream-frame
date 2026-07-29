package com.cream.base.spel.entity;

import java.lang.reflect.Method;

/**
 * SpEL 实体
 *
 * @param method      目标方法
 * @param args        方法参数
 * @param targetClass 目标类的类型信息
 * @author Cream
 * @since 2026-07-12 20:09
 */
public record SpELObject(Method method, Object[] args, Class<?> targetClass) {

}
