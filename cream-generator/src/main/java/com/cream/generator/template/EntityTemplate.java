package com.cream.generator.template;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.ITableAnnotationHandler;
import com.baomidou.mybatisplus.generator.ITableFieldAnnotationHandler;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import com.cream.generator.config.TemplateConf;
import com.cream.generator.handler.TableAnnotationHandler;
import com.cream.generator.handler.TableFieldAnnotationHandler;
import com.cream.mpj.base.entity.po.BaseEntity;

import java.util.Arrays;
import java.util.List;

/**
 * EntityTemplate 配置类
 *
 * @author Cream
 * @since 2026-06-09 22:33
 */
public class EntityTemplate {

    // 	名称转换实现
    private static final INameConvert nameConvert = null;
    // 	设置父类
    private static final Class<?> superClass = BaseEntity.class;
    // 	禁用生成 serialVersionUID
    private static final Boolean disableSerialVersionUID = false;
    // 	覆盖已生成文件
    private static final Boolean enableFileOverride = true;
    // 	开启生成字段常量
    private static final Boolean enableColumnConstant = false;
    // 	开启链式模型
    private static final Boolean enableChainModel = true;
    // 	开启 Lombok 模型
    private static final Boolean enableLombok = true;
    // 	开启 Boolean 类型字段移除 is 前缀
    private static final Boolean enableRemoveIsPrefix = false;
    // 	开启生成实体时生成字段注解
    private static final Boolean enableTableFieldAnnotation = true;
    // 	开启 ActiveRecord 模型
    private static final Boolean enableActiveRecord = true;
    // 	逻辑删除字段名
    private static final String logicDeletePropertyName = "deleted";
    // 	数据库表映射到实体的命名策略
    private static final NamingStrategy naming = NamingStrategy.underline_to_camel;
    // 	数据库表字段映射到实体的命名策略
    private static final NamingStrategy columnNaming = null;
    // 	添加父类公共字段
    private static final String[] addSuperEntityColumns = Arrays.asList("deleted", "create_by", "create_name", "create_time", "update_by", "update_name", "update_time", "version").toArray(new String[0]);
    // 	添加忽略字段
    private static final String[] addIgnoreColumns = null;
    // 	添加表字段填充
    private static final List<IFill> addTableFills = null;
    // 	全局主键类型
    private static final IdType idType = IdType.ASSIGN_ID;
    // 	转换文件名称
    private static final ConverterFileName convertFileName = null;
    // 	格式化文件名称
    private static final String formatEntityFileName = "%s";
    // 	是否生成ToString方法
    private static final Boolean toString = true;
    // 	启用字段文档注释
    private static final Boolean fieldUseJavaDoc = true;

    // 	添加实体类注解
    private static final ClassAnnotationAttributes data = new ClassAnnotationAttributes("@Data", "lombok.Data");
    // 生成hashCode时，带上父类的字段
    private static final ClassAnnotationAttributes equalsAndHashCode = new ClassAnnotationAttributes("@EqualsAndHashCode(callSuper = true)", "lombok.EqualsAndHashCode");
    // 无参构造函数
    private static final ClassAnnotationAttributes noArgsConstructor = new ClassAnnotationAttributes("@NoArgsConstructor", "lombok.NoArgsConstructor");
    // 全参构造函数
    private static final ClassAnnotationAttributes allArgsConstructor = new ClassAnnotationAttributes("@AllArgsConstructor", "lombok.AllArgsConstructor");

    // 	表注解处理器
    private static final ITableAnnotationHandler tableAnnotationHandler = new TableAnnotationHandler();
    // 	字段注解处理器
    private static final ITableFieldAnnotationHandler tableFieldAnnotationHandler = new TableFieldAnnotationHandler();

    /**
     * 初始化 entity 模板
     *
     * @param strategyConfig             策略配置
     * @author Cream
     * @since 2026-06-09 22:34
     */
    public static void init(StrategyConfig strategyConfig) {
        init(strategyConfig, formatEntityFileName);
    }

    /**
     * 初始化 entity 模板
     *
     * @param strategyConfig             策略配置
     * @param formatEntityFileName       格式化文件名称
     * @author Cream
     * @since 2026-06-09 22:34
     */
    public static void init(StrategyConfig strategyConfig, String formatEntityFileName) {
        init(strategyConfig,nameConvert, superClass, disableSerialVersionUID, enableFileOverride, enableColumnConstant, enableChainModel,
                enableLombok, enableRemoveIsPrefix, enableTableFieldAnnotation, enableActiveRecord, logicDeletePropertyName,
                naming, columnNaming, addSuperEntityColumns, addIgnoreColumns, addTableFills, idType, convertFileName,
                formatEntityFileName, toString, fieldUseJavaDoc);
    }

    /**
     * 初始化 entity 模板
     *
     * @param strategyConfig             策略配置
     * @param nameConvert                名称转换
     * @param superClass                 父类
     * @param disableSerialVersionUID    禁用生成 serialVersionUID
     * @param enableFileOverride         覆盖已生成文件
     * @param enableColumnConstant       开启生成字段常量
     * @param enableChainModel           开启链式模型
     * @param enableLombok               开启 Lombok 模型
     * @param enableRemoveIsPrefix       开启 Boolean 类型字段移除 is 前缀
     * @param enableTableFieldAnnotation 开启生成实体时生成字段注解
     * @param enableActiveRecord         开启 ActiveRecord 模型
     * @param logicDeletePropertyName    逻辑删除字段名
     * @param naming                     数据库表映射到实体的命名策略
     * @param columnNaming               数据库表字段映射到实体的命名策略
     * @param addSuperEntityColumns      添加父类公共字段
     * @param addIgnoreColumns           添加忽略字段
     * @param addTableFills              添加表字段填充
     * @param idType                     全局主键类型
     * @param convertFileName            转换文件名称
     * @param formatEntityFileName       格式化文件名称
     * @param toString                   是否生成ToString方法
     * @param fieldUseJavaDoc            启用字段文档注释
     * @author Cream
     * @since 2026-06-09 22:34
     */
    public static void init(StrategyConfig strategyConfig, INameConvert nameConvert, Class<?> superClass, Boolean disableSerialVersionUID,
                            Boolean enableFileOverride, Boolean enableColumnConstant, Boolean enableChainModel,
                            Boolean enableLombok, Boolean enableRemoveIsPrefix,
                            Boolean enableTableFieldAnnotation, Boolean enableActiveRecord,
                            String logicDeletePropertyName, NamingStrategy naming, NamingStrategy columnNaming,
                            String[] addSuperEntityColumns, String[] addIgnoreColumns, List<IFill> addTableFills,
                            IdType idType, ConverterFileName convertFileName, String formatEntityFileName,
                            Boolean toString, Boolean fieldUseJavaDoc) {

        Entity.Builder builder = strategyConfig.entityBuilder();
        if (nameConvert != null) {
            builder.nameConvert(nameConvert);
        }
        if (superClass != null) {
            builder.superClass(superClass);
        }
        if (disableSerialVersionUID) {
            builder.disableSerialVersionUID();
        }
        if (enableFileOverride) {
            builder.enableFileOverride();
        }
        if (enableColumnConstant) {
            builder.enableColumnConstant();
        }
        if (enableChainModel) {
            builder.enableChainModel();
        }
        if (enableLombok) {
            builder.enableLombok(data, equalsAndHashCode, noArgsConstructor, allArgsConstructor);
        }
        if (enableRemoveIsPrefix) {
            builder.enableRemoveIsPrefix();
        }
        if (enableTableFieldAnnotation) {
            builder.enableTableFieldAnnotation();
        }
        if (enableActiveRecord) {
            builder.enableActiveRecord();
        }
        if (logicDeletePropertyName != null) {
            builder.logicDeletePropertyName(logicDeletePropertyName);
        }
        if (columnNaming != null) {
            builder.columnNaming(columnNaming);
        } else if (naming != null) {
            builder.naming(naming);
        }
        if (addSuperEntityColumns != null) {
            builder.addSuperEntityColumns(addSuperEntityColumns);
        }
        if (addIgnoreColumns != null) {
            builder.addIgnoreColumns(addIgnoreColumns);
        }
        if (addTableFills != null) {
            builder.addTableFills(addTableFills);
        }
        if (idType != null) {
            builder.idType(idType);
        }
        if (convertFileName != null) {
            builder.convertFileName(convertFileName);
        }
        if (formatEntityFileName != null) {
            builder.formatFileName(formatEntityFileName);
        }
        builder.toString(toString);
        builder.fieldUseJavaDoc(fieldUseJavaDoc);
        builder.javaTemplate(TemplateConf.entity);
        builder.tableAnnotationHandler(tableAnnotationHandler);
        builder.tableFieldAnnotationHandler(tableFieldAnnotationHandler);
    }
}
