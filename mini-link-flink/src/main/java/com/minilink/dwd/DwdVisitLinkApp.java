package com.minilink.dwd;

import com.minilink.util.KafkaFlinkUtil;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-23  15:30
 * @Description: 访问短链接埋点 DWD
 * @Version: 1.0
 */
public class DwdVisitLinkApp {
    /**
     * @see com.minilink.constant.KafkaConstant
     */
    private static String ODS_VISIT_LINK_TOPIC = "ods_visit_link_topic";
    private static String VISIT_LINK_GROUP_ID = "visit_link_group_id";


    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FlinkKafkaConsumer kafkaConsumer = KafkaFlinkUtil.getKafkaConsumer(ODS_VISIT_LINK_TOPIC, VISIT_LINK_GROUP_ID);
        DataStreamSource ds = env.addSource(kafkaConsumer);
        ds.print();
        env.execute();
    }
}
