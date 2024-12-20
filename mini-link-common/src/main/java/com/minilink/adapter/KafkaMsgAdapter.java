package com.minilink.adapter;

import com.minilink.pojo.entity.VisitShortLinkMsg;
import com.minilink.util.ClientUtil;

import java.time.LocalDateTime;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-20  11:20
 * @Description: Kafka 队列消息适配器
 * @Version: 1.0
 */
public class KafkaMsgAdapter {
    public static VisitShortLinkMsg buildVisitShortLinkMsg(String shortLinkCode) {

        VisitShortLinkMsg msg = new VisitShortLinkMsg();
        msg.setIp(ClientUtil.getIpAddr());
        msg.setShortLinkCode(shortLinkCode);
        msg.setDeviceType(ClientUtil.getDeviceType().getName());
        msg.setOsType(ClientUtil.getOsName());
        msg.setBrowserType(ClientUtil.getBrowserGroup().getName());
        msg.setVisitTime(LocalDateTime.now());
        return msg;
    }
}
