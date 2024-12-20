package com.minilink.constant;

/**
 * @Author 徐志斌
 * @Date: 2024/12/7 9:14
 * @Version 1.0
 * @Description: Redis Key常量
 */
public class RedisConstant {
    /**
     * 验证码 Key
     */
    public static final String CAPTCHA_KEY = "account:captcha";
    public static final String EMAIL_CODE_KEY = "account:email-code";
    public static final String EMAIL_CHECK_KEY = "account:email-check";

    /**
     * Spring Cache Key
     */
    public static final String CACHE_LINK_USER = "cache:link-user";
    public static final String CACHE_LINK_URL_TOB = "cache:link-url-tob";
    public static final String CACHE_LINK_URL_TOC = "cache:link-url-toc";

}
