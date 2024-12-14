package com.minilink.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
    private String accountId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

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
