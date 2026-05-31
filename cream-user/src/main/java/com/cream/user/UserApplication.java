package com.cream.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * User模块启动类
 *
 * @author Cream
 * @since 2026-05-17 14:26
 */
@SpringBootApplication
@ComponentScan(value = "com.cream")
@MapperScan(value = "com.cream.*.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
