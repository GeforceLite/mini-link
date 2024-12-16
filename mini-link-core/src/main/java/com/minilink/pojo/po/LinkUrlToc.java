package com.minilink.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2024-12-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("link_url_toc")
public class LinkUrlToc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

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
     * 到期时间
     */
    @TableField("expired_time")
    private LocalDateTime expiredTime;
}
