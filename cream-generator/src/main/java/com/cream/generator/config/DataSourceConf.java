package com.cream.generator.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.cream.generator.handler.CustomTypeConvertHandler;
import lombok.Data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

/**
 * DataSourceConfig 配置类
 *
 * @author Cream
 * @since 2026-06-09 21:41
 */
@Data
public class DataSourceConf {

    // 数据库产品名称（通过JDBC元数据获取，初始化时缓存）
    private static String dbProductName;

    public static DataSourceConfig init(String jdbcUrl, String username, String password) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(jdbcUrl, username, password).typeConvertHandler(new CustomTypeConvertHandler()).build();

        // 提前获取数据库产品名称并缓存
        try (Connection conn = dataSourceConfig.getConn()) {
            DatabaseMetaData metaData = conn.getMetaData();
            dbProductName = metaData.getDatabaseProductName().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("获取数据库元数据失败", e);
        }

        return dataSourceConfig;
    }

}
