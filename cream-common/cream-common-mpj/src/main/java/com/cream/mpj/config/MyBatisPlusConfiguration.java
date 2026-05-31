package com.cream.mpj.config;

import com.cream.mpj.inteceptor.SqlBeautyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置类
 *
 * @author Cream
 * @since 2026-05-31 21:09
 */
@Configuration
public class MyBatisPlusConfiguration {

    @Bean
    public SqlBeautyInterceptor sqlBeautyInterceptor() {
        return  new SqlBeautyInterceptor();
    }
}
