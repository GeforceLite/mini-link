package com.minilink.constant;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-20  14:08
 * @Description: 公用常量
 * @Version: 1.0
 */
public class CommonConstant {
    public static final String REGEX_SHORT_LINK_FORMAT = "^\\d+-\\d+-[a-z0-9A-Z]+$";
    public static final String REGEX_LONG_LINK_FORMAT = "^https?://.*";
    public static final String REGEX_EMAIL_FORMAT = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";

    public static final String HEADER_TOKEN_KEY = "mini-link-token";
}
