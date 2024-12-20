package com.minilink.util;

import eu.bitwalker.useragentutils.*;
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

    public static String getBrowserName() {
        Browser browser = getBrowser();
        return browser.getName();
    }

    public static BrowserType getBrowserType() {
        Browser browser = getBrowser();
        return browser.getBrowserType();
    }

    public static Browser getBrowserGroup() {
        Browser browser = getBrowser();
        return browser.getGroup();
    }

    public static String getOsName() {
        OperatingSystem operatingSystem = getOperatingSystem();
        return operatingSystem.getName();
    }

    public static DeviceType getDeviceType() {
        OperatingSystem operatingSystem = getOperatingSystem();
        return operatingSystem.getDeviceType();
    }

    public static OperatingSystem getOsGroup() {
        OperatingSystem operatingSystem = getOperatingSystem();
        return operatingSystem.getGroup();
    }

    private static UserAgent getUserAgent() {
        HttpServletRequest request = HttpServletUtil.getRequest();
        String agent = request.getHeader("User-Agent");
        return UserAgent.parseUserAgentString(agent);
    }

    private static Browser getBrowser() {
        UserAgent userAgent = getUserAgent();
        return userAgent.getBrowser();
    }

    private static OperatingSystem getOperatingSystem() {
        HttpServletRequest request = HttpServletUtil.getRequest();
        String agent = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        return userAgent.getOperatingSystem();
    }
}
