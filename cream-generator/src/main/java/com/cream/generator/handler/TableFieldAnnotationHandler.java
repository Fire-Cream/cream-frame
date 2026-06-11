package com.cream.generator.handler;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.generator.ITableFieldAnnotationHandler;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.model.AnnotationAttributes;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 字段注解处理器
 *
 * @author Cream
 * @since 2026-06-09 22:29
 */
public class TableFieldAnnotationHandler implements ITableFieldAnnotationHandler {

    @Override
    public List<AnnotationAttributes> handle(TableInfo tableInfo, TableField tableField) {
        List<AnnotationAttributes> annotationAttributesList = new ArrayList<>();
        GlobalConfig globalConfig = tableInfo.getGlobalConfig();
        String comment = tableField.getComment();

        // 处理Swagger/SpringDoc注解
        if (StringUtils.isNotBlank(comment)) {
            if (globalConfig.isSpringdoc()) {
                String displayName = String.format("@Schema(description = \"%s\")", comment);
                annotationAttributesList.add(new AnnotationAttributes(displayName, "io.swagger.v3.oas.annotations.media.Schema"));
            } else if (globalConfig.isSwagger()) {
                String displayName = String.format("@ApiModelProperty(\"%s\")", comment);
                annotationAttributesList.add(new AnnotationAttributes(displayName, "io.swagger.annotations.ApiModelProperty"));
            }
        }

        // 处理主键注解
        if (tableField.isKeyFlag()) {
            handleKeyAnnotation(tableField, annotationAttributesList);
        }
        // 处理非主键注解
        else {
            // 普通字段注解
            handleCommonFieldAnnotation(tableField, annotationAttributesList);

            if (tableField.isVersionField()) {
                // @Version
                annotationAttributesList.add(new AnnotationAttributes(Version.class));
            }
            if (tableField.isLogicDeleteField()) {
                //@TableLogic
                annotationAttributesList.add(new AnnotationAttributes(TableLogic.class));
            }
        }
        return annotationAttributesList;
    }

    /**
     * 处理主键字段注解
     */
    private void handleKeyAnnotation(TableField tableField, List<AnnotationAttributes> annotationAttributesList) {
        IdType idType = tableField.getEntity().getIdType();
        String columnName = tableField.getAnnotationColumnName();

        if (tableField.isKeyIdentityFlag()) {
            //@TableId(value = "${field.annotationColumnName}", type = IdType.AUTO)
            annotationAttributesList.add(createTableIdAnnotation(columnName, "IdType.AUTO"));
        } else if (idType != null) {
            //@TableId(value = "${field.annotationColumnName}", type = IdType.${idType})
            annotationAttributesList.add(createTableIdAnnotation(columnName, "IdType." + idType.name()));
        } else if (tableField.isConvert()) {
            //@TableId("${field.annotationColumnName}")
            annotationAttributesList.add(createAnnotationAttr(TableId.class.getName(), "\"" + columnName + "\""));
        }
    }

    /**
     * 处理普通字段注解，增加LocalDate类型的JdbcType处理
     */
    private void handleCommonFieldAnnotation(TableField tableField, List<AnnotationAttributes> annotationAttributesList) {
        String columnName = tableField.getAnnotationColumnName();
        String fill = tableField.getFill();
        // 获取字段的Java类型
        String javaType = tableField.getPropertyType();

        // 构建TableField注解的属性
        List<String> attributes = new ArrayList<>();

        // 添加字段映射
        if (tableField.isConvert()) {
            // 字段名映射：@TableField(value = "${field.annotationColumnName}")
            attributes.add("value = \"" + columnName + "\"");
        }

        // 添加填充策略
        if (StringUtils.isNotBlank(fill)) {
            // 填充策略：@TableField(fill = FieldFill.${field.fill})
            attributes.add("fill = FieldFill." + fill);
        }
        System.out.println("目标值: " + LocalDate.class.getSimpleName());

        // 为LocalDate类型添加JdbcType.DATE
        if (javaType != null && javaType.equals(LocalDate.class.getSimpleName())) {
            attributes.add("jdbcType = JdbcType.DATE");
        }
        System.out.println("目标值: " + LocalDateTime.class.getSimpleName());

        // 为LocalDateTime类型添加JdbcType.TIMESTAMP（可选）
        if (javaType != null && javaType.equals(LocalDateTime.class.getSimpleName())) {
            attributes.add("jdbcType = JdbcType.TIMESTAMP");
        }

        // 生成TableField注解
        if (!attributes.isEmpty()) {
            String attrStr = String.join(", ", attributes);
            annotationAttributesList.add(createAnnotationAttr(com.baomidou.mybatisplus.annotation.TableField.class.getName(), attrStr));
        }
    }

    /**
     * 创建TableId注解
     */
    private AnnotationAttributes createTableIdAnnotation(String columnName, String idType) {
        return createAnnotationAttr(TableId.class.getName(), String.format("value = \"%s\", type = %s", columnName, idType));
    }

    /**
     * 创建注解属性对象
     */
    private AnnotationAttributes createAnnotationAttr(String className, String attributes) {
        String displayName = String.format("@%s(%s)", className.substring(className.lastIndexOf(".") + 1), attributes);
        return new AnnotationAttributes(displayName, className);
    }


}
