package com.minilink.app.dwd;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.minilink.app.func.DwdClickLinkStateMapFunction;
import com.minilink.constant.KafkaConstant;
import com.minilink.util.FlinkKafkaUtil;
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
public class DwdClickLinkApp {
    public static final String SOURCE_TOPIC = KafkaConstant.ODS_CLICK_LINK_TOPIC;
    public static final String SINK_TOPIC = KafkaConstant.DWD_CLICK_LINK_TOPIC;
    public static final String DWD_CLICK_LINK_GROUP = KafkaConstant.DWD_CLICK_LINK_GROUP;

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FlinkKafkaConsumer kafkaConsumer = FlinkKafkaUtil.getKafkaConsumer(SOURCE_TOPIC, DWD_CLICK_LINK_GROUP);
        DataStreamSource jsonStrDS = env.addSource(kafkaConsumer);
        jsonStrDS.print("----------DWD-接收到ODS队列消息----------");

        SingleOutputStreamOperator<JSONObject> jsonObjDS = jsonStrDS.flatMap(
                new FlatMapFunction<String, JSONObject>() {
                    @Override
                    public void flatMap(String msg, Collector collector) {
                        JSONObject jsonObj = JSONUtil.toBean(msg, JSONObject.class);
                        collector.collect(jsonObj);
                    }
                }
        );

        KeyedStream keyedStream = jsonObjDS.keyBy(
                new KeySelector<JSONObject, String>() {
                    @Override
                    public String getKey(JSONObject jsonStr) {
                        return jsonStr.getStr("userAgent");
                    }
                }
        );

        SingleOutputStreamOperator<String> visitorStateDS = keyedStream.map(new DwdClickLinkStateMapFunction());
        FlinkKafkaProducer kafkaProducer = FlinkKafkaUtil.getKafkaProducer(SINK_TOPIC);
        visitorStateDS.addSink(kafkaProducer);
        visitorStateDS.print("----------DWD-标记新老客后数据----------");
        env.execute();
    }
}
