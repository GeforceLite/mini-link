package com.minilink.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 徐志斌
 * @Date: 2024/12/9 13:55
 * @Version 1.0
 * @Description: 操作主库注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Master {

}
