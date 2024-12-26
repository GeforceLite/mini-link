package com.minilink.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-23  09:57
 * @Description: 访客状态枚举
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum VisitorStateEnum {
    NEW("0", "新访客"),
    OLD("1", "老访客");

    private String code;
    private String msg;
}
