package com.cream.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc 配置类
 *
 * @author Cream
 * @since 2026-06-15 21:17
 */
@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(description = "开发环境服务器", url = "http://localhost:8080")
        }
)
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // 配置接口文档基本信息
                .info(this.getApiInfo());
    }

    private Info getApiInfo() {
        return new Info()
                // 配置文档标题
                .title("SpringBoot3集成Swagger3")
                // 配置文档描述
                .description("SpringBoot3集成Swagger3示例文档")
                // 配置版本号
                .version("1.0.0");
    }

}