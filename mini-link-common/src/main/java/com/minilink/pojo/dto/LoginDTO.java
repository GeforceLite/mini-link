package com.minilink.pojo.dto;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2024/12/7 21:42
 * @Version 1.0
 * @Description: 登录表单DTO
 */
@Data
public class LoginDTO {
    /**
     * 邮箱
     */
    private String email;

    /**
     * 输入密码
     */
    private String password;

    /**
     * 邮箱验证码
     */
    private String emailCode;
}
