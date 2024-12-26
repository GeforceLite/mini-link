package com.minilink.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 徐志斌
 * @Date: 2024/12/21 7:47
 * @Version 1.0
 * @Description: ToB短链接VO
 */
@Data
public class LinkUrlTobVO {
    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 账号
     */
    private Long accountId;

    /**
     * 链接标题
     */
    private String title;

    /**
     * 链接icon
     */
    private String icon;

    /**
     * 短链接
     */
    private String shortLink;

    /**
     * 长链接
     */
    private String longLink;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 到期时间
     */
    private LocalDateTime expiredTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
