package com.minilink.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-09
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("link_url_tob")
public class LinkUrlTob implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 分组id
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * 账号
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 链接标题
     */
    @TableField("title")
    private String title;

    /**
     * 链接icon
     */
    @TableField("icon")
    private String icon;

    /**
     * 短链接
     */
    @TableField("short_link")
    private String shortLink;

    /**
     * 长链接
     */
    @TableField("long_link")
    private String longLink;

    /**
     * 二维码
     */
    @TableField("qr_code")
    private String qrCode;

    /**
     * 到期时间
     */
    @TableField("expired_time")
    private LocalDateTime expiredTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标识
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;
}
