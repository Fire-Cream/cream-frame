package com.cream.base.spel.utils;

import com.cream.base.spel.entity.SpELEvaluationContext;
import com.cream.base.spel.entity.SpELObject;
import com.cream.base.spel.evaluator.SpELEvaluator;

import java.lang.reflect.Method;

/**
 * 工作流切面实现
 *
 * @author Cream
 * @since 2026-07-31 14:04
 */
public class SpELUtils {

    private static final SpELEvaluator evaluator = new SpELEvaluator();

    /**
     * 构建上下文实体
     *
     * @param method 方法对象
     * @param args 方法参数列表
     * @return com.gykj.base.spel.entity.SpELEvaluationContext
     */
    public static SpELEvaluationContext build(Method method, Object[] args) {
        SpELObject spELObject = new SpELObject(method, args);
        return new SpELEvaluationContext(spELObject, evaluator.getDiscoverer());
    }

    /**
     * 解析上下文实体
     *
     * @param expression 表达式
     * @param context    日志表达式上下文
     * @return T 表达式结果
     */
    public static <T> T parse(String expression, SpELEvaluationContext context) {
        return evaluator.parse(expression, context);
    }

}
