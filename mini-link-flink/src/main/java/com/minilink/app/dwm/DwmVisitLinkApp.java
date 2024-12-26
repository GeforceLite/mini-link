package com.minilink.app.dwm;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.minilink.constant.KafkaConstant;
import com.minilink.pojo.VisitShortLinkMsgLog;
import com.minilink.util.DateTimeUtil;
import com.minilink.util.FlinkKafkaUtil;
import com.minilink.util.UserAgentUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-26  11:20
 * @Description: 访问短链接埋点 DWM
 * @Version: 1.0
 */
public class DwmVisitLinkApp {
    public static final String SOURCE_TOPIC = KafkaConstant.DWD_VISIT_LINK_TOPIC;
    public static final String SINK_TOPIC = KafkaConstant.DWM_VISIT_LINK_TOPIC;
    public static final String DWS_VISIT_LINK_GROUP = KafkaConstant.DWM_VISIT_LINK_GROUP;

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setParallelism(1);
        FlinkKafkaConsumer kafkaConsumer = FlinkKafkaUtil.getKafkaConsumer(SOURCE_TOPIC, DWS_VISIT_LINK_GROUP);
        DataStreamSource jsonStrDS = env.addSource(kafkaConsumer);

        // 数据补齐：访问设备相关
        SingleOutputStreamOperator<VisitShortLinkMsgLog> addDeviceDS = jsonStrDS.flatMap(
                new FlatMapFunction<String, VisitShortLinkMsgLog>() {
                    @Override
                    public void flatMap(String jsonStr, Collector collector) {
                        JSONObject jsonObj = JSONUtil.toBean(jsonStr, JSONObject.class);
                        String userAgentStr = jsonObj.getStr("userAgent");
                        String ip = jsonObj.getStr("ip");
                        String browserType = UserAgentUtil.getBrowserType(userAgentStr);
                        String osType = UserAgentUtil.getOsType(userAgentStr);
                        String deviceType = UserAgentUtil.getDeviceType(userAgentStr);
                        String visitTimeStamp = jsonObj.getStr("visitTime");
                        String visitorState = jsonObj.getStr("visitorState");

                        VisitShortLinkMsgLog msgLog = new VisitShortLinkMsgLog();
                        msgLog.setIp(ip);
                        msgLog.setVisitorState(visitorState);
                        msgLog.setBrowserType(browserType);
                        msgLog.setOsType(osType);
                        msgLog.setDeviceType(deviceType);
                        msgLog.setVisitTime(DateTimeUtil.timeStampToLocalDateTime(Long.valueOf(visitTimeStamp)));
                        collector.collect(msgLog);
                    }
                }
        );
        addDeviceDS.print("----------DWM-访问行为数据补充----------");

//        // 数据补齐：访问地址相关
//        SingleOutputStreamOperator<VisitShortLinkMsgLog> addRegionDS = addDeviceDS.flatMap(
//                new FlatMapFunction<VisitShortLinkMsgLog, VisitShortLinkMsgLog>() {
//                    @Override
//                    public void flatMap(VisitShortLinkMsgLog jsonStr, Collector collector) {
//
//                    }
//                }
//        );
        env.execute();
    }
}
