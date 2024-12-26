package com.minilink.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-17  16:15
 * @Description: 浏览器指纹解析-工具类
 * @Version: 1.0
 */
public class UserAgentUtil {
    public static UserAgent getUserAgent(String userAgentStr) {
        return UserAgent.parseUserAgentString(userAgentStr);
    }

    public static String getBrowserType(String userAgentStr) {
        Browser browser = getUserAgent(userAgentStr).getBrowser();
        return browser.getGroup().getName();
    }

    public static String getOsType(String userAgentStr) {
        OperatingSystem operatingSystem = getUserAgent(userAgentStr).getOperatingSystem();
        return operatingSystem.getGroup().getName();
    }

    public static String getDeviceType(String userAgentStr) {
        OperatingSystem operatingSystem = getUserAgent(userAgentStr).getOperatingSystem();
        return operatingSystem.getDeviceType().getName();
    }
}
