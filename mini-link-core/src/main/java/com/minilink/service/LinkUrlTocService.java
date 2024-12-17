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
     * @param shortLink 短链接
     */
    void redirect(String shortLink);
}
