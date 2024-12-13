package com.minilink.service;

import com.minilink.pojo.dto.LinkUrlSaveDTO;

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
     * @param saveDTO 保存链接入参DTO
     */
    void createShortLink(LinkUrlSaveDTO saveDTO);
}
