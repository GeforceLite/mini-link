package com.minilink.pojo;

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
     * 短链接码
     */
    private String shortLinkCode;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 操作系统类型
     */
    private String osType;

    /**
     * 浏览器类型
     */
    private String browserType;

    /**
     * 访问网络
     */
    private String netWork;

    /**
     * 访问时间
     */
    private LocalDateTime visitTime;
}
