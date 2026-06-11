package com.cream.generator.config;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.io.File;

/**
 * GlobalConfig 配置类
 *
 * @author Cream
 * @since 2026-06-09 21:48
 */
public class GlobalConf {

    /**
     * @since 全局配置
     */
    // 禁止自动打开输出目录
    public static final Boolean disableOpenDir = true;
    // 指定代码生成的输出目录
    public static final String outputDir = System.getProperty("user.dir") + File.separator + "generate";
    // 作者
    public static final String author = "Cream";
    // 开启 Kotlin 模式
    public static final Boolean enableKotlin = false;
    // 开启 Swagger 模式
    public static final Boolean enableSwagger = true;
    // 设置时间类型策略
    public static final DateType dateType = DateType.ONLY_DATE;
    // 设置注释日期格式
    public static final String commentDate = "yyyy-MM-dd HH:mm:ss";

    /**
     * 初始化全局配置
     *
     * @param author 作者
     * @return com.baomidou.mybatisplus.generator.config.GlobalConfig
     * @author Cream
     * @since 2026-06-09 21:52
     */
    public static GlobalConfig init(String author, String outputDir) {
        return init(disableOpenDir, outputDir, author, enableKotlin, enableSwagger, dateType, commentDate);
    }

    /**
     * 初始化全局配置
     *
     * @param disableOpenDir 禁止自动打开输出目录
     * @param outputDir 指定代码生成的输出目录
     * @param author 作者
     * @param enableKotlin 开启 Kotlin 模式
     * @param enableSwagger 开启 Swagger 模式
     * @param dateType 设置时间类型策略
     * @param commentDate 设置注释日期格式
     * @return com.baomidou.mybatisplus.generator.config.GlobalConfig
     * @author Cream
     * @since 2026-06-09 21:52
     */
    public static GlobalConfig init(Boolean disableOpenDir, String outputDir, String author, Boolean enableKotlin, Boolean enableSwagger, DateType dateType, String commentDate) {
        GlobalConfig.Builder builder = new GlobalConfig.Builder();
        if (disableOpenDir) {
            builder.disableOpenDir();
        }
        builder.outputDir(outputDir);
        builder.author(author);
        if (enableKotlin) {
            builder.enableKotlin();
        }
        if (enableSwagger) {
            builder.enableSwagger();
        }
        builder.dateType(dateType);
        builder.commentDate(commentDate);
        return builder.build();
    }

}
