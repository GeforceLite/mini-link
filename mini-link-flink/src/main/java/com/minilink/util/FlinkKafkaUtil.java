package com.minilink.util;

import com.minilink.constant.KafkaConstant;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-23  15:55
 * @Description: Flink Kafka 工具类
 * @Version: 1.0
 */
public class FlinkKafkaUtil {

    /**
     * 获取 Flink Kafka 生产者
     *
     * @param topic 主题
     * @return 生产者
     */
    public static FlinkKafkaProducer getKafkaProducer(String topic) {
        return new FlinkKafkaProducer(KafkaConstant.KAFKA_SERVER, topic, new SimpleStringSchema());
    }

    /**
     * 获取 Flink Kafka 消费者
     *
     * @param topic   主题
     * @param groupId 消费组
     * @return 消费者
     */
    public static FlinkKafkaConsumer getKafkaConsumer(String topic, String groupId) {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_SERVER);
        return new FlinkKafkaConsumer(topic, new SimpleStringSchema(), props);
    }
}
