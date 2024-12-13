package com.minilink.service;

import com.minilink.pojo.dto.LinkUrlSaveDTO;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-09
 */
public interface LinkUrlService {
    /**
     * 生成短链接
     *
     * @param saveDTO 保存入参DTO
     */
    void createShortLink(LinkUrlSaveDTO saveDTO);

    /**
     * 访问短链接
     *
     * @param shortLink 短链接
     */
    void redirect(String shortLink) throws IOException;
}
