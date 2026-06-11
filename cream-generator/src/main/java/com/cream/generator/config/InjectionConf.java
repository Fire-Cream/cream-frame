package com.cream.generator.config;

import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InjectionConfig 配置类
 *
 * @author Cream
 * @since 2026-06-09 21:55
 */
public class InjectionConf {

    // 自定义配置 Map 对象
    private static final Map<String, Object> customMap = new HashMap<>();
    // 自定义配置模板文件
    private static final List<CustomFile> customFiles = new ArrayList<>();

    static {
        initCustomMap();
        initCustomFiles();
    }

    /**
     * 初始化注入配置
     *
     * @return com.baomidou.mybatisplus.generator.config.InjectionConfig
     * @author Cream
     * @since 2026-06-09 21:57
     */
    public static InjectionConfig init() {
        return init(customMap, customFiles);
    }

    /**
     * 初始化注入配置
     *
     * @param customMap 自定义配置 Map 对象
     * @param customFiles 自定义配置模板文件
     * @return com.baomidou.mybatisplus.generator.config.InjectionConfig
     * @author Cream
     * @since 2026-06-09 21:58
     */
    public static InjectionConfig init(Map<String, Object> customMap, List<CustomFile> customFiles) {
        InjectionConfig.Builder builder = new InjectionConfig.Builder();
        if (customMap != null){
            builder.customMap(customMap);
        }
        if (customFiles != null) {
            builder.customFile(customFiles);
        }
        return builder.build();
    }

    /**
     * 初始化自定义配置 Map 对象
     *
     * @author Cream
     * @since 2026-06-09 21:59
     */
    public static void initCustomMap() {
        customMap.put("idClass", "String");
    }

    /**
     * 初始化自定义配置模板文件
     *
     * @author Cream
     * @since 2026-06-09 21:59
     */
    public static void initCustomFiles() {
        // 基础dto
        customFiles.add(new CustomFile.Builder().packageName("entity.dto.query").fileName("QueryPageDto.java").templatePath(TemplateConf.queryPageDto).enableFileOverride().build());
        customFiles.add(new CustomFile.Builder().packageName("entity.dto.query").fileName("QueryDto.java").templatePath(TemplateConf.queryDto).enableFileOverride().build());
        customFiles.add(new CustomFile.Builder().packageName("entity.dto.save").fileName("SaveDto.java").templatePath(TemplateConf.saveDto).enableFileOverride().build());
        customFiles.add(new CustomFile.Builder().packageName("entity.dto.update").fileName("UpdateDto.java").templatePath(TemplateConf.updateDto).enableFileOverride().build());
        // 基础vo
        customFiles.add(new CustomFile.Builder().packageName("entity.vo").fileName("PageVo.java").templatePath(TemplateConf.pageVo).enableFileOverride().build());
        customFiles.add(new CustomFile.Builder().packageName("entity.vo").fileName("ListVo.java").templatePath(TemplateConf.listVo).enableFileOverride().build());
        customFiles.add(new CustomFile.Builder().packageName("entity.vo").fileName("Vo.java").templatePath(TemplateConf.vo).enableFileOverride().build());
    }
}
