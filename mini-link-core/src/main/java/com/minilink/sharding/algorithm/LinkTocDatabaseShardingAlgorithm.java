package com.minilink.sharding.algorithm;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;

/**
 * @Author 徐志斌
 * @Date: 2024/12/15 16:26
 * @Version 1.0
 * @Description: ToC短链接-分库算法
 */
public class LinkTocDatabaseShardingAlgorithm implements StandardShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String shortLink = preciseShardingValue.getValue();
        String databaseName = "ds";
        String databaseCode = shortLink.split("-")[0];
        return databaseName + databaseCode;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        return null;
    }
}
