package com.minilink.service;

import com.minilink.pojo.dto.LinkUrlSaveDTO;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-09
 */
public interface LinkUrlTobService {
    /**
     * 生成短链接
     *
     * @param saveDTO 保存入参DTO
     */
    void createShortLink(LinkUrlSaveDTO saveDTO);

    /**
     * 获取目标链接 User-Agent 信息
     *
     * @param link 目标链接
     * @return User-Agent信息
     */
    Map<String, Object> parseLink(String link) throws IOException;
}
