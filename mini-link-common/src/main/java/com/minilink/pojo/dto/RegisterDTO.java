package com.minilink.pojo.dto;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2024/12/7 21:31
 * @Version 1.0
 * @Description: 注册表单DTO
 */
@Data
public class RegisterDTO {
    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮箱验证码
     */
    private String emailCode;

    /**
     * 输入密码
     */
    private String password1;

    /**
     * 确认密码
     */
    private String password2;
}
