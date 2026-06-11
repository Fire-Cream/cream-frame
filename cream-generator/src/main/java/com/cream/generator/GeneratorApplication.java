package com.cream.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.cream.generator.config.*;
import com.cream.generator.template.ControllerTemplate;
import com.cream.generator.template.EntityTemplate;
import com.cream.generator.template.MapperTemplate;
import com.cream.generator.template.ServiceTemplate;

import java.io.File;
import java.util.*;

/**
 * 代码生成器
 *
 * @author Cream
 * @since 2026-06-09 21:40
 */
public class GeneratorApplication {

    // jdbc 路径
    public static final String jdbcUrl = "jdbc:mysql://10.99.11.200:3306/cream-frame";
    // 数据库账号
    public static final String username = "root";
    // 数据库密码
    public static final String password = "123456";
    // 作者
    public static final String author = "Cream";
    // 父包名
    public static final String parent = "com.cream";
    // 父包模块名
    public static final String moduleName = "user";
    // 需要生成的表名
    public static final List<String> includes = Arrays.asList(
            "sys_user",
            ""
    );
    // 生成代码所在模块
    public static String modelName = "cream-user";

    public static void main(String[] args) {
        // 初始化数据源
        DataSourceConfig dataSourceConfig = DataSourceConf.init(jdbcUrl, username, password);
        // 初始化globalConfig
        GlobalConfig globalConfig = GlobalConf.init(author, System.getProperty("user.dir") + File.separator + modelName + "/src/main/java");
        // 初始化packageConfig
        PackageConfig packageConfig = PackageConf.init(parent, moduleName);
        // 初始化strategyConfig
        StrategyConfig strategyConfig = StrategyConf.init(includes, false);
        // 初始化entity配置
        EntityTemplate.init(strategyConfig);
        // 初始化controller配置
        ControllerTemplate.init(strategyConfig);
        // 初始化service配置
        ServiceTemplate.init(strategyConfig);
        // 初始化mapper配置
        MapperTemplate.init(strategyConfig);
        // 初始化injectionConfig
        InjectionConfig injectionConfig = InjectionConf.init();

        // 装配配置，生成代码
        AutoGenerator generator = new AutoGenerator(dataSourceConfig);
        generator.global(globalConfig);
        generator.packageInfo(packageConfig);
        generator.strategy(strategyConfig);
        generator.injection(injectionConfig);
        generator.execute();
    }

}
