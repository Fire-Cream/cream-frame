package com.cream.mpj.config;

import com.cream.mpj.inteceptor.SqlBeautyInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus 配置类
 *
 * @author Cream
 * @since 2026-05-31 21:09
 */
@Configuration
public class MyBatisPlusConfiguration {

    @Bean
    @ConditionalOnBooleanProperty(name = {"mpj.beauty-show"}, matchIfMissing = true)
    public SqlBeautyInterceptor sqlBeautyInterceptor() {
        return  new SqlBeautyInterceptor();
    }
}
