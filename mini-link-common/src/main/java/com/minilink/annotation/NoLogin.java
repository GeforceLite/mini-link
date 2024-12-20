package com.minilink.annotation;

import java.lang.annotation.*;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-20  11:48
 * @Description: 免登录注解
 * @Version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLogin {
}
