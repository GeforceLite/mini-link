package com.minilink.sharding.algorithm;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;

/**
 * @Author 徐志斌
 * @Date: 2024/12/15 16:26
 * @Version 1.0
 * @Description: ToC短链接-分表算法
 */
public class LinkTocTableShardingAlgorithm implements StandardShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String shortLink = preciseShardingValue.getValue();
        String[] array = shortLink.split("-");
        String tableCode = array[1];
        return "link_url_toc_" + tableCode;

    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        return null;
    }
}
