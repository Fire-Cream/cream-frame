package com.cream.generator.template;

import com.baomidou.mybatisplus.generator.IGenerateMapperMethodHandler;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.cream.generator.config.TemplateConf;
import com.cream.mpj.base.mapper.BaseMPJMapper;
import org.apache.ibatis.cache.Cache;

import java.lang.annotation.Annotation;

/**
 * MapperTemplate 配置类
 *
 * @author Cream
 * @since 2026-06-09 22:39
 */
public class MapperTemplate {

    // 设置父类
    private static final Class<?> superClass = BaseMPJMapper.class;
    // 覆盖已生成文件
    private static final Boolean enableFileOverride = true;
    // 开启 @Mapper 注解
    private static final Class<? extends Annotation> mapperAnnotation = org.apache.ibatis.annotations.Mapper.class;
    // 启用 BaseResultMap 生成
    private static final Boolean enableBaseResultMap = true;
    // 启用 BaseColumnList
    private static final Boolean enableBaseColumnList = true;
    // 设置缓存实现类
    private static final Class<? extends Cache> cache = null;
    // 转换 Mapper 类文件名称
    private static final ConverterFileName convertMapperFileName = null;
    // 转换 XML 文件名称
    private static final ConverterFileName convertXmlFileName = null;
    // 格式化 Mapper 文件名称
    private static final String formatMapperFileName = "%sMapper";
    // 格式化 XML 实现类文件名称
    private static final String formatXmlFileName = "%sMapper";
    // 自定义生成Mapper方法实现
    private static final IGenerateMapperMethodHandler generateMapperMethodHandler = null;

    /**
     * 初始化 mapper 模板
     *
     * @param strategyConfig 策略配置
     * @author Cream
     * @since 2026-06-09 22:40
     */
    public static void init(StrategyConfig strategyConfig) {
        init(strategyConfig, formatMapperFileName, formatXmlFileName);
    }

    /**
     * 初始化 mapper 模板
     *
     * @param strategyConfig 策略配置
     * @param formatMapperFileName 格式化 Mapper 文件名称
     * @param formatXmlFileName 格式化 XML 实现类文件名称
     * @author Cream
     * @since 2026-06-09 22:40
     */
    public static void init(StrategyConfig strategyConfig, String formatMapperFileName, String formatXmlFileName) {
        init(strategyConfig, superClass, formatMapperFileName, formatXmlFileName);
    }

    /**
     * 初始化 mapper 模板
     *
     * @param strategyConfig 策略配置
     * @param superClass 父类
     * @param formatMapperFileName 格式化 Mapper 文件名称
     * @param formatXmlFileName 格式化 XML 实现类文件名称
     * @author Cream
     * @since 2026-06-09 22:40
     */
    public static void init(StrategyConfig strategyConfig, Class<?> superClass, String formatMapperFileName, String formatXmlFileName) {
        init(strategyConfig, superClass, enableFileOverride, mapperAnnotation, enableBaseResultMap, enableBaseColumnList, cache, convertMapperFileName, convertXmlFileName, formatMapperFileName, formatXmlFileName, generateMapperMethodHandler);
    }

    /**
     * 初始化 mapper 模板
     *
     * @param strategyConfig 策略配置
     * @param superClass 父类
     * @param enableFileOverride 覆盖已生成文件
     * @param mapperAnnotation 开启 @Mapper 注解
     * @param enableBaseResultMap 启用 BaseResultMap 生成
     * @param enableBaseColumnList 启用 BaseColumnList
     * @param cache 设置缓存实现类
     * @param convertMapperFileName 转换 Mapper 类文件名称
     * @param convertXmlFileName 转换 XML 文件名称
     * @param formatMapperFileName 格式化 Mapper 文件名称
     * @param formatXmlFileName 格式化 XML 实现类文件名称
     * @param generateMapperMethodHandler 自定义生成Mapper方法实现
     * @author Cream
     * @since 2026-06-09 22:40
     */
    public static void init(StrategyConfig strategyConfig, Class<?> superClass, Boolean enableFileOverride,
                            Class<? extends Annotation> mapperAnnotation, Boolean enableBaseResultMap,
                            Boolean enableBaseColumnList, Class<? extends Cache> cache,
                            ConverterFileName convertMapperFileName, ConverterFileName convertXmlFileName,
                            String formatMapperFileName, String formatXmlFileName,
                            IGenerateMapperMethodHandler generateMapperMethodHandler) {
        Mapper.Builder builder = strategyConfig.mapperBuilder();
        if (superClass != null) {
            builder.superClass(superClass);
        }
        if (enableFileOverride) {
            builder.enableFileOverride();
        }
        if (mapperAnnotation != null) {
            builder.mapperAnnotation(mapperAnnotation);
        }
        if (enableBaseResultMap) {
            builder.enableBaseResultMap();
        }
        if (enableBaseColumnList) {
            builder.enableBaseColumnList();
        }
        if (cache != null) {
            builder.cache(cache);
        }
        if (convertMapperFileName != null) {
            builder.convertMapperFileName(convertMapperFileName);
        }
        if (convertXmlFileName != null) {
            builder.convertXmlFileName(convertXmlFileName);
        }
        if (formatMapperFileName != null) {
            builder.formatMapperFileName(formatMapperFileName);
        }
        if (formatXmlFileName != null) {
            builder.formatXmlFileName(formatXmlFileName);
        }
        if (generateMapperMethodHandler != null) {
            builder.generateMapperMethodHandler(generateMapperMethodHandler);
        }
        builder.mapperTemplate(TemplateConf.mapper);
        builder.mapperXmlTemplate(TemplateConf.mapperXml);
    }
}
