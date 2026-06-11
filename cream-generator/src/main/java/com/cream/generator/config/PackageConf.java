package com.cream.generator.config;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

import java.util.Map;

/**
 * PackageConfig 配置类
 *
 * @author Cream
 * @since 2026-06-09 21:59
 */
public class PackageConf {

    /**
     * @since 包配置
     */
    // 父包名
    public static final String parent = "com.ashgod";
    // 父包模块名
    public static final String moduleName = "generate";
    // Entity 包名
    public static final String entity = "entity.po";
    // Service 包名
    public static final String service = "service";
    // Service Impl 包名
    public static final String serviceImpl = "service.impl";
    // Mapper 包名
    public static final String mapper = "mapper";
    // Mapper XML 包名
    public static final String mapperXml = "mapper.xml";
    // Controller 包名
    public static final String controller = "controller";
    // 设置路径配置信息
    public static final Map<OutputFile, String> pathInfo = null;

    /**
     * 初始化包配置
     *
     * @param parent 父包名
     * @param moduleName 父包模块名
     * @return com.baomidou.mybatisplus.generator.config.PackageConfig
     * @author Cream
     * @since 2026-06-09 22:02
     */
    public static PackageConfig init(String parent, String moduleName) {
        return init(parent, moduleName, pathInfo);
    }

    /**
     * 初始化包配置
     *
     * @param parent 父包名
     * @param moduleName 父包模块名
     * @param pathInfo 路径配置信息
     * @return com.baomidou.mybatisplus.generator.config.PackageConfig
     * @author Cream
     * @since 2026-06-09 22:02
     */
    public static PackageConfig init(String parent, String moduleName, Map<OutputFile, String> pathInfo) {
        PackageConfig.Builder builder = new PackageConfig.Builder();
        builder.parent(parent);
        builder.moduleName(moduleName);
        builder.entity(entity);
        builder.service(service);
        builder.serviceImpl(serviceImpl);
        builder.mapper(mapper);
        builder.xml(mapperXml);
        builder.controller(controller);
        builder.pathInfo(pathInfo);
        return builder.build();
    }

}
