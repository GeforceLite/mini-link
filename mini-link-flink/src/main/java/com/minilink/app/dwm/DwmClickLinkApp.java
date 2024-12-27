package com.minilink.app.dwm;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.minilink.constant.KafkaConstant;
import com.minilink.pojo.VisitShortLinkWideLog;
import com.minilink.util.AMapUtil;
import com.minilink.util.DateTimeUtil;
import com.minilink.util.FlinkKafkaUtil;
import com.minilink.util.UserAgentUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Map;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-26  11:20
 * @Description: 访问短链接埋点 DWM
 * @Version: 1.0
 */
public class DwmClickLinkApp {
    public static final String SOURCE_TOPIC = KafkaConstant.DWD_CLICK_LINK_TOPIC;
    public static final String SINK_TOPIC = KafkaConstant.DWM_CLICK_LINK_TOPIC;
    public static final String DWS_CLICK_LINK_GROUP = KafkaConstant.DWM_CLICK_LINK_GROUP;

    public static void main(String[] args) throws Exception {
        // 接收上游数据 DWD
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FlinkKafkaConsumer kafkaConsumer = FlinkKafkaUtil.getKafkaConsumer(SOURCE_TOPIC, DWS_CLICK_LINK_GROUP);
        DataStreamSource jsonStrDS = env.addSource(kafkaConsumer);

        // 数据补齐：访问设备相关
        SingleOutputStreamOperator<VisitShortLinkWideLog> addDeviceDS = jsonStrDS.flatMap(
                new FlatMapFunction<String, VisitShortLinkWideLog>() {
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

                        VisitShortLinkWideLog msgLog = new VisitShortLinkWideLog();
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
        addDeviceDS.print("----------DWM-访问设备数据补充----------");

        // 数据补齐：访问地址相关
        SingleOutputStreamOperator<VisitShortLinkWideLog> addRegionDS = addDeviceDS.flatMap(
                new FlatMapFunction<VisitShortLinkWideLog, VisitShortLinkWideLog>() {
                    @Override
                    public void flatMap(VisitShortLinkWideLog wideLog, Collector collector) {
                        Map<String, String> locationMap = AMapUtil.getLocationByIp(wideLog.getIp());
                        wideLog.setProvince(locationMap.get(locationMap.get("province")));
                        wideLog.setCity(locationMap.get(locationMap.get("city")));
                        collector.collect(wideLog);
                    }
                }
        );
        addRegionDS.print("----------DWM-访问地区数据补充----------");

        // TODO 独立访客数据聚合


        // TODO 数据推送到下游 DWS

        env.execute();
    }
}
