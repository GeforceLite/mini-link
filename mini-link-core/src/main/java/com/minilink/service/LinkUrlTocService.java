package com.minilink.service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-14
 */
public interface LinkUrlTocService {
    /**
     * 访问短链接
     *
     * @param shortLinkCode 短链接码
     */
    void redirect(String shortLinkCode);
}
