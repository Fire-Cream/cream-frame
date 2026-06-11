package com.cream.generator.config;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;

import java.util.List;

/**
 * StrategyConfig 配置类
 *
 * @author Cream
 * @since 2026-06-09 22:06
 */
public class StrategyConf {

    /**
     * 初始化策略配置
     *
     * @param includes 包含的表名
     * @param enableSchema 启用 schema
     * @return com.baomidou.mybatisplus.generator.config.StrategyConfig
     * @author Cream
     * @since 2026-06-09 22:08
     */
    public static StrategyConfig init(List<String> includes, Boolean enableSchema) {
        StrategyConfig.Builder strategyConfig = new StrategyConfig.Builder();
        strategyConfig.enableCapitalMode();
        strategyConfig.enableSkipView();
        strategyConfig.disableSqlFilter();
        if (enableSchema) {
            strategyConfig.enableSchema();
        }
        strategyConfig.addInclude(includes);
        return strategyConfig.build();
    }

}
