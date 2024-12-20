package com.minilink.config;

import com.minilink.constant.CommonConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-20  16:14
 * @Description: Swagger 配置类
 * @Version: 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi usersGroup() {
        return GroupedOpenApi.builder()
                .group("users")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList(CommonConstant.HEADER_TOKEN_KEY));
                    return operation;
                })
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components();
        components.addSecuritySchemes(CommonConstant.HEADER_TOKEN_KEY,
                new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .scheme("basic")
                        .name(CommonConstant.HEADER_TOKEN_KEY)
                        .in(SecurityScheme.In.HEADER)
                        .description("请求头")
        );
        return new OpenAPI()
                .components(components)
                .info(apiInfo());
    }

    private Info apiInfo() {
        Contact contact = new Contact();
        contact.setEmail("binx19980707@gmail.com");
        contact.setName("徐志斌");
        contact.setUrl("https://xuzhibin.blog.csdn.net");
        return new Info()
                .title("Mini Link 接口文档")
                .version("1.0")
                .contact(contact)
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }
}
