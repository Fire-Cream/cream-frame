package com.cream.generator.template;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.cream.generator.config.TemplateConf;
import com.cream.mpj.base.service.BaseMPJService;
import com.cream.mpj.base.service.impl.BaseMPJServiceImpl;

/**
 * ServiceTemplate 配置类
 *
 * @author Cream
 * @since 2026-06-09 22:42
 */
public class ServiceTemplate {

    // 设置 Service 接口父类
    private static final Class<?> superServiceClass = BaseMPJService.class;
    // 设置 ServiceImpl 实现类父类
    private static final Class<?> superServiceImplClass = BaseMPJServiceImpl.class;
    // 覆盖已生成文件
    private static final Boolean enableFileOverride = true;
    // 转换 Service 接口文件名称
    private static final ConverterFileName convertServiceFileName = null;
    // 转换 Service 实现类文件名称
    private static final ConverterFileName convertServiceImplFileName = null;
    // 格式化 Service 接口文件名称
    private static final String formatServiceFileName = "%sService";
    // 格式化 Service 实现类文件名称
    private static final String formatServiceImplFileName = "%sServiceImpl";

    /**
     * 初始化 service 模板
     *
     * @param strategyConfig             策略配置
     * @author Cream
     * @since 2026-06-09 22:42
     */
    public static void init(StrategyConfig strategyConfig) {
        init(strategyConfig, superServiceClass, superServiceImplClass);
    }

    /**
     * 初始化 service 模板
     *
     * @param strategyConfig             策略配置
     * @param superServiceClass          Service 接口父类
     * @param superServiceImplClass      ServiceImpl 实现类父类
     * @author Cream
     * @since 2026-06-09 22:42
     */
    public static void init(StrategyConfig strategyConfig, Class<?> superServiceClass, Class<?> superServiceImplClass) {
        init(strategyConfig, superServiceClass, superServiceImplClass, formatServiceFileName, formatServiceImplFileName);
    }

    /**
     * 初始化 service 模板
     *
     * @param strategyConfig             策略配置
     * @param superServiceClass          Service 接口父类
     * @param superServiceImplClass      ServiceImpl 实现类父类
     * @param formatServiceFileName      格式化 Service 接口文件名称
     * @param formatServiceImplFileName  格式化 Service 实现类文件名称
     * @author Cream
     * @since 2026-06-09 22:42
     */
    public static void init(StrategyConfig strategyConfig, Class<?> superServiceClass, Class<?> superServiceImplClass, String formatServiceFileName, String formatServiceImplFileName) {
        init(strategyConfig, superServiceClass, superServiceImplClass, enableFileOverride, convertServiceFileName, convertServiceImplFileName, formatServiceFileName, formatServiceImplFileName);
    }

    /**
     * 初始化 service 模板
     *
     * @param strategyConfig             策略配置
     * @param superServiceClass          Service 接口父类
     * @param superServiceImplClass      ServiceImpl 实现类父类
     * @param enableFileOverride         覆盖已生成文件
     * @param convertServiceFileName     转换 Service 接口文件名称
     * @param convertServiceImplFileName 转换 Service 实现类文件名称
     * @param formatServiceFileName      格式化 Service 接口文件名称
     * @param formatServiceImplFileName  格式化 Service 实现类文件名称
     * @author Cream
     * @since 2026-06-09 22:42
     */
    public static void init(StrategyConfig strategyConfig, Class<?> superServiceClass, Class<?> superServiceImplClass,
                            Boolean enableFileOverride, ConverterFileName convertServiceFileName,
                            ConverterFileName convertServiceImplFileName, String formatServiceFileName,
                            String formatServiceImplFileName) {
        Service.Builder builder = strategyConfig.serviceBuilder();
        if (superServiceClass != null) {
            builder.superServiceClass(superServiceClass);
        }
        if (superServiceImplClass != null) {
            builder.superServiceImplClass(superServiceImplClass);
        }
        if (enableFileOverride) {
            builder.enableFileOverride();
        }
        if (convertServiceFileName != null) {
            builder.convertServiceFileName(convertServiceFileName);
        }
        if (convertServiceImplFileName != null) {
            builder.convertServiceImplFileName(convertServiceImplFileName);
        }
        if (formatServiceFileName != null) {
            builder.formatServiceFileName(formatServiceFileName);
        }
        if (formatServiceImplFileName != null) {
            builder.formatServiceImplFileName(formatServiceImplFileName);
        }
        builder.serviceTemplate(TemplateConf.service);
        builder.serviceImplTemplate(TemplateConf.serviceImpl);
    }
}
