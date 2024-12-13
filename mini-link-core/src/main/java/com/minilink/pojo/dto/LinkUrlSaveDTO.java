package com.minilink.pojo.dto;

import jakarta.validation.constraints.NotBlank;
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
     * 标题
     */
    @NotBlank(message = "标题不存在")
    private String title;

    /**
     * 长链接
     */
    @NotBlank(message = "长链接不存在")
    private String longLink;

    /**
     * 到期时间
     */
    private LocalDateTime expiredTime;
}
