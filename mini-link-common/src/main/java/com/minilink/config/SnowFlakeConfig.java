package com.minilink.config;

import org.springframework.context.annotation.Configuration;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author 徐志斌
 * @Date: 2024/12/15 15:14
 * @Version 1.0
 * @Description: 雪花算法SnowFlake配置类
 */
@Configuration
public class SnowFlakeConfig {
    /**
     * 通过 Ipv4 地址哈希取模生成 worker-id
     */
    static {
        InetAddress inetAddress = null;
        try {
            inetAddress = Inet4Address.getLocalHost();
            String hostAddressIp = inetAddress.getHostAddress();
            String workerId = Math.abs(hostAddressIp.hashCode()) % 1024 + "";
            System.setProperty("workerId", workerId);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
