package com.minilink.consumer;

import com.minilink.constant.KafkaConstant;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-20  10:39
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class TestConsumer {
    @KafkaListener(topics = KafkaConstant.CLICK_LINK_LOG_TOPIC, groupId = KafkaConstant.CLICK_LINK_LOG_GROUP_ID)
    public void consumer(String msg) {
        System.out.println("收到消息：" + msg);
    }
}
