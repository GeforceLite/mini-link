package com.minilink.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-23  09:57
 * @Description: 邮件枚举
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum EmailEnum {
    REGISTER(1, "注册表单邮件"),
    ;

    private int code;
    private String msg;
}
