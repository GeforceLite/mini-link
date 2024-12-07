package com.minilink.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author 徐志斌
 * @Date: 2024/04/28 09:21
 * @Description: Swagger配置类
 * @Version 1.0
 * -----------------------------------------------------------------
 * 文档访问地址：http://localhost:端口/swagger-ui/index.html
 * 添加Knife4j可以导出导出离线文档，访问地址：http://localhost:端口/doc.html
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI restfulOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Mini Link API 接口文档"));
    }
}
