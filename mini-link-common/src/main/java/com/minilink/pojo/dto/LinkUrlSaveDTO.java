package com.minilink.pojo.dto;

import com.minilink.constant.LinkUrlConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  13:28
 * @Description: 链接保存入参DTO
 * @Version: 1.0
 */
@Data
public class LinkUrlSaveDTO {
    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 链接 icon
     */
    private String icon;

    /**
     * 标题
     */
    @NotBlank(message = "标题信息不能为空")
    private String title;

    /**
     * 长链接（目标链接）
     */
    @NotBlank(message = "长链接信息不能为空")
    @Pattern(regexp = LinkUrlConstant.LONG_LINK_FORMAT_REGEX, message = "长链接格式不正确")
    private String longLink;

    /**
     * 到期时间
     */
    private LocalDateTime expiredTime;
}
