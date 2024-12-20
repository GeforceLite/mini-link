package com.minilink.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 徐志斌
 * @Date: 2024/12/8 17:22
 * @Version 1.0
 * @Description: Token 拦截器配置
 */
@Configuration
public class LoginInterceptorHandler implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    private static final List<String> SWAGGER_EXCLUDE_PATH = Arrays.asList(
            "/doc.html",
            "/swagger**/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v3/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(SWAGGER_EXCLUDE_PATH);
    }
}
