package com.cream.generator.handler;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.type.ITypeConvertHandler;
import com.baomidou.mybatisplus.generator.type.TypeRegistry;
import org.jetbrains.annotations.NotNull;

import java.sql.Types;

/**
 * 自定义类型转换 Handler
 *
 * @author Cream
 * @since 2026-06-09 21:43
 */
public class CustomTypeConvertHandler implements ITypeConvertHandler {

    @Override
    public @NotNull IColumnType convert(GlobalConfig globalConfig, TypeRegistry typeRegistry, TableField.MetaInfo metaInfo) {
        // 获取数据库类型
        int typeCode = metaInfo.getJdbcType().TYPE_CODE;
        // DATE / TIME / TIMESTAMP / TIMESTAMP_WITH_TIMEZONE → LocalDate（仅日期）
        if (typeCode == Types.DATE || typeCode == Types.TIME || typeCode == Types.TIMESTAMP || typeCode == Types.TIMESTAMP_WITH_TIMEZONE) {
            return DbColumnType.LOCAL_DATE_TIME;
        }
        // 其他类型使用默认转换
        return typeRegistry.getColumnType(metaInfo);
    }

}
