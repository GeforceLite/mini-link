package com.minilink.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-17  16:15
 * @Description: 客户端解析工具类
 * @Version: 1.0
 */
public class ClientUtil {
    public static String getIpAddr() {
        HttpServletRequest request = HttpServletUtil.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1).trim();
        }
        return ip;
    }

    public static UserAgent getUserAgent() {
        HttpServletRequest request = HttpServletUtil.getRequest();
        String userAgentStr = request.getHeader("User-Agent");
        return UserAgent.parseUserAgentString(userAgentStr);
    }

    public static String getBrowserType() {
        Browser browser = getUserAgent().getBrowser();
        return browser.getGroup().getName();
    }

    public static String getOsType() {
        OperatingSystem operatingSystem = getUserAgent().getOperatingSystem();
        return operatingSystem.getGroup().getName();
    }

    public static String getDeviceType() {
        OperatingSystem operatingSystem = getUserAgent().getOperatingSystem();
        return operatingSystem.getDeviceType().getName();
    }
}
