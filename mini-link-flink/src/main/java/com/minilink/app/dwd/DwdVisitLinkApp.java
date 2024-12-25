package com.minilink.app.dwd;

import cn.hutool.json.JSONUtil;
import com.minilink.app.func.VisitorStateMapFunction;
import com.minilink.constant.KafkaConstant;
import com.minilink.pojo.VisitShortLinkMsg;
import com.minilink.util.KafkaFlinkUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.Collector;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-23  15:30
 * @Description: 访问短链接埋点 DWD
 * @Version: 1.0
 */
public class DwdVisitLinkApp {
    /**
     * ODS
     *  |
     * DWD：当前位置
     *  |
     * DWM
     *  |
     * DWS
     */
    public static final String SOURCE_TOPIC = KafkaConstant.ODS_VISIT_LINK_TOPIC;
    public static final String SINK_TOPIC = KafkaConstant.DWD_VISIT_LINK_TOPIC;

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FlinkKafkaConsumer kafkaConsumer = KafkaFlinkUtil.getKafkaConsumer(SOURCE_TOPIC, KafkaConstant.VISIT_LINK_GROUP);
        DataStreamSource jsonStrDS = env.addSource(kafkaConsumer);
        jsonStrDS.print("----------点击短链接行为数据----------");

        // 字符串信息转对象
        SingleOutputStreamOperator<VisitShortLinkMsg> jsonObjDS = jsonStrDS.flatMap(
                new FlatMapFunction<String, VisitShortLinkMsg>() {
                    @Override
                    public void flatMap(String msg, Collector collector) {
                        VisitShortLinkMsg visitShortLinkMsg = JSONUtil.toBean(msg, VisitShortLinkMsg.class);
                        collector.collect(visitShortLinkMsg);
                    }
                }
        );
        jsonObjDS.print("----------字符转实体类数据----------");

        // 根据设备类型分组
        KeyedStream keyedStream = jsonObjDS.keyBy(
                new KeySelector<VisitShortLinkMsg, String>() {
                    @Override
                    public String getKey(VisitShortLinkMsg msg) throws Exception {
                        return msg.getDeviceType();
                    }
                }
        );


        // 新老客标记，下沉
        jsonObjDS = keyedStream.map(new VisitorStateMapFunction());
        FlinkKafkaProducer kafkaProducer = KafkaFlinkUtil.getKafkaProducer(SINK_TOPIC, KafkaConstant.VISIT_LINK_GROUP);
        jsonObjDS.addSink(kafkaProducer);
        jsonObjDS.print("----------标记新老客后数据----------");
        env.execute();
    }
}
