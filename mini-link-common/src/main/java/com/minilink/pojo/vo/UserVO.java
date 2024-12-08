package com.minilink.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 徐志斌
 * @Date: 2024/12/8 19:45
 * @Version 1.0
 * @Description: 账户信息VO
 */
@Data
public class UserVO {
    /**
     * 账号
     */
    @TableField("email")
    private String email;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
