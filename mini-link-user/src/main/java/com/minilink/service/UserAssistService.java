package com.minilink.service;

import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

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
public interface UserAssistService {
    /**
     * 图片验证码
     */
    void captcha() throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 获取客户端图片验证码 Redis Key
     */
    String getCaptchaKey() throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 发送邮件
     *
     * @param email 邮箱
     */
    void sendEmail(String email) throws MessagingException;

    /**
     * 上传头像
     *
     * @param avatarFile 头像文件
     */
    void uploadAvatar(MultipartFile avatarFile);
}
