package com.cream.base.spel.evaluator;

import com.cream.base.spel.entity.SpELEvaluationContext;
import lombok.Getter;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * SpEL 解析器
 *
 * @author Cream
 * @since 2026-07-12 20:10
 */
@Getter
public class SpELEvaluator {

    /**
     * SpEL解析器
     */
    private final SpelExpressionParser parser = new SpelExpressionParser();

    /**
     * 参数解析器
     */
    private final ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

    /**
     * 表达式模板
     */
    private final ParserContext template = new TemplateParserContext("#{", "}");

    /**
     * 解析
     *
     * @param expression 表达式
     * @param context    日志表达式上下文
     * @return T 表达式结果
     */
    @SuppressWarnings("unchecked")
    public <T> T parse(String expression, SpELEvaluationContext context) {
        return (T) getExpression(expression).getValue(context);
    }

    /**
     * 获取翻译后表达式
     *
     * @param expression 字符串表达式
     * @return 翻译后表达式
     */
    private Expression getExpression(String expression) {
        return getParser().parseExpression(expression, template);
    }

}
