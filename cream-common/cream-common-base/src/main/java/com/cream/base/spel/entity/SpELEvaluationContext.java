package com.cream.base.spel.entity;

import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

/**
 * SpEL 上下文解析实体
 *
 * @author Cream
 * @since 2026-07-12 20:12
 */
public class SpELEvaluationContext extends MethodBasedEvaluationContext {

    /**
     * 构造方法
     *
     * @param rootObject 数据来源对象
     * @param discoverer 参数解析器
     */
    public SpELEvaluationContext(SpELObject rootObject, ParameterNameDiscoverer discoverer) {
        super(rootObject, rootObject.method(), rootObject.args(), discoverer);
    }

}