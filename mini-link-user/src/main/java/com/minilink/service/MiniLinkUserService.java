package com.minilink.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
public interface MiniLinkUserService {
    /**
     * 图片验证码
     */
    void captcha() throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 发送邮件
     *
     * @param email 邮箱
     */
    void sendEmail(String email);
}
