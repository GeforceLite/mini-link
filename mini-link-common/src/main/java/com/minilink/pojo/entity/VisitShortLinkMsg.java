package com.minilink.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-20  11:21
 * @Description: 访问短链接-队列消息
 * @Version: 1.0
 */
@Data
public class VisitShortLinkMsg {
    /**
     * ip
     */
    private String ip;

    /**
     * 浏览器指纹
     */
    private String userAgent;

    /**
     * 访问时间
     */
    private LocalDateTime visitTime;
}
