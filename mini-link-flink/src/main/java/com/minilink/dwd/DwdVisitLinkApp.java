package com.minilink.dwd;

import cn.hutool.json.JSONUtil;
import com.minilink.pojo.VisitShortLinkMsg;
import com.minilink.util.KafkaFlinkUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

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
        DataStreamSource streamSource = env.addSource(kafkaConsumer);
        streamSource.print();

        // 数据监听采集
        SingleOutputStreamOperator streamOperator = streamSource.flatMap(new FlatMapFunction<String, Object>() {
            @Override
            public void flatMap(String msg, Collector collector) throws Exception {
                VisitShortLinkMsg visitShortLinkMsg = JSONUtil.toBean(msg, VisitShortLinkMsg.class);
                collector.collect(visitShortLinkMsg);
            }
        });

        // 分组：根据设备类型
        KeyedStream keyedStream = streamOperator.keyBy(new KeySelector<VisitShortLinkMsg, String>() {
            @Override
            public String getKey(VisitShortLinkMsg msg) throws Exception {
                return msg.getDeviceType();
            }
        });

        // 识别新老客
//        keyedStream.addSink();

        env.execute();
    }
}
