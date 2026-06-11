package com.cream.generator.handler;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.ITableAnnotationHandler;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 表注解生成器
 *
 * @author Cream
 * @since 2026-06-09 22:27
 */
public class TableAnnotationHandler implements ITableAnnotationHandler {

    // 	添加实体类注解
    private static final ClassAnnotationAttributes data = new ClassAnnotationAttributes("@Data", "lombok.Data");
    // 生成hashCode时，带上父类的字段
    private static final ClassAnnotationAttributes equalsAndHashCode = new ClassAnnotationAttributes("@EqualsAndHashCode(callSuper = true)", "lombok.EqualsAndHashCode");
    // 无参构造函数
    private static final ClassAnnotationAttributes noArgsConstructor = new ClassAnnotationAttributes("@NoArgsConstructor", "lombok.NoArgsConstructor");
    // 全参构造函数
    private static final ClassAnnotationAttributes allArgsConstructor = new ClassAnnotationAttributes("@AllArgsConstructor", "lombok.AllArgsConstructor");
    // 链式调用
    private static final ClassAnnotationAttributes accessors = new ClassAnnotationAttributes("@Accessors(chain = true)", "lombok.experimental.Accessors");

    @Override
    public List<ClassAnnotationAttributes> handle(TableInfo tableInfo, Entity entity) {
        List<ClassAnnotationAttributes> annotationAttributesList = new ArrayList<>();
        GlobalConfig globalConfig = tableInfo.getGlobalConfig();
        String comment = tableInfo.getComment();
        // 若表注释为空，用空字符串兜底，避免空指针
        if (StringUtils.isBlank(comment)) {
            comment = StringPool.EMPTY;
        }
        boolean isKotlin = globalConfig.isKotlin();

        // Kotlin场景暂不处理以下注解（兼容原有模板逻辑）
        if (!isKotlin && entity.isLombok()) {
            if (entity.isDefaultLombok()) {
                // @Data
                annotationAttributesList.add(data);
                // @EqualsAndHashCode
                annotationAttributesList.add(equalsAndHashCode);
                // @NoArgsConstructor
                annotationAttributesList.add(noArgsConstructor);
                // @AllArgsConstructor
                annotationAttributesList.add(allArgsConstructor);
            }

            // 处理Lombok的@Accessors注解（链式调用）
            // 仅当使用Lombok时且开启链式调用添加
            if (entity.isChain()) {
                annotationAttributesList.add(accessors);
            }
        }

        // 处理表名映射注解@TableName
        // 当数据库表名与实体类名需要转换时（如下划线转驼峰），显式添加注解
        if (tableInfo.isConvert()) {
            String schemaName = tableInfo.getSchemaName();
            // 拼接schema名（如数据库 schema 不为空则加前缀，否则忽略）
            if (StringUtils.isBlank(schemaName)) {
                schemaName = StringPool.EMPTY;
            } else {
                schemaName = schemaName + StringPool.DOT; // 格式：schema.表名
            }
            //@TableName("${schemaName}${table.name}")
            String displayName = String.format("@TableName(\"%s%s\")", schemaName, tableInfo.getName());
            annotationAttributesList.add(new ClassAnnotationAttributes(TableName.class, displayName));
        }

        // 处理Swagger注解@ApiModel（旧版Swagger）
        if (globalConfig.isSwagger()) {
            //@ApiModel(value = "${entity}对象", description = "${table.comment!}")
            String displayName = String.format("@ApiModel(value = \"%s对象\", description = \"%s\")", tableInfo.getEntityName(), comment);
            annotationAttributesList.add(new ClassAnnotationAttributes(
                    displayName, "io.swagger.annotations.ApiModel", "io.swagger.annotations.ApiModelProperty"));
        }

        // 处理SpringDoc注解@Schema（新版OpenAPI）
        if (globalConfig.isSpringdoc()) {
            //@Schema(name = "${entity}", description = "${table.comment!}")
            String displayName = String.format("@Schema(name = \"%s\", description = \"%s\")", tableInfo.getEntityName(), comment);
            annotationAttributesList.add(new ClassAnnotationAttributes(displayName, "io.swagger.v3.oas.annotations.media.Schema"));
        }

        return annotationAttributesList;
    }
}
