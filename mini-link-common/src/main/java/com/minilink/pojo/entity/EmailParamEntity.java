package com.minilink.pojo.entity;

import lombok.Data;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-23  10:13
 * @Description: 发送邮件参数
 * @Version: 1.0
 */
@Data
public class EmailParamEntity {
    /**
     * 接收人邮件
     */
    private String email;

    /**
     * 邮件验证码
     */
    private String emailCode;
}
