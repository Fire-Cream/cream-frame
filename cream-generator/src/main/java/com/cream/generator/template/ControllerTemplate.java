package com.cream.generator.template;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.cream.generator.config.TemplateConf;
import com.cream.mpj.base.controller.BaseMPJController;

/**
 * ControllerTemplate 配置类
 *
 * @author Cream
 * @since 2026-06-09 22:30
 */
public class ControllerTemplate {

    // 设置父类
    private static final Class<?> superClass = BaseMPJController.class;
    // 覆盖已生成文件
    private static final Boolean enableFileOverride = true;
    // 开启驼峰转连字符
    private static final Boolean enableHyphenStyle = false;
    // 开启生成@RestController 控制器
    private static final Boolean enableRestStyle = true;
    // 转换文件名称
    private static final ConverterFileName convertFileName = null;
    // 格式化文件名称
    private static final String formatControllerFileName = "%sController";

    /**
     * 初始化 controller 模板
     *
     * @param strategyConfig 策略配置
     * @author Cream
     * @since 2026-06-09 22:31
     */
    public static void init(StrategyConfig strategyConfig) {
        init(strategyConfig, superClass, enableFileOverride, enableHyphenStyle, enableRestStyle, convertFileName,
                formatControllerFileName);
    }

    /**
     * 初始化 controller 模板
     *
     * @param strategyConfig 策略配置
     * @param superClass 父类
     * @param enableFileOverride 覆盖已生成文件
     * @param enableHyphenStyle 开启驼峰转连字符
     * @param enableRestStyle 开启生成@RestController 控制器
     * @param convertFileName 转换文件名称
     * @param formatControllerFileName 格式化文件名称
     * @author Cream
     * @since 2026-06-09 22:31
     */
    public static void init(StrategyConfig strategyConfig, Class<?> superClass, Boolean enableFileOverride,
                            Boolean enableHyphenStyle, Boolean enableRestStyle, ConverterFileName convertFileName,
                            String formatControllerFileName) {
        Controller.Builder builder = strategyConfig.controllerBuilder();
        if (superClass != null) {
            builder.superClass(superClass);
        }
        if (enableFileOverride) {
            builder.enableFileOverride();
        }
        if (enableHyphenStyle) {
            builder.enableHyphenStyle();
        }
        if (enableRestStyle) {
            builder.enableRestStyle();
        }
        if (convertFileName != null) {
            builder.convertFileName(convertFileName);
        }
        if (formatControllerFileName != null) {
            builder.formatFileName(formatControllerFileName);
        }
        builder.template(TemplateConf.controller);
    }

}
