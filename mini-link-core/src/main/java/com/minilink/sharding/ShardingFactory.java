package com.minilink.sharding;

import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  10:44
 * @Description: 库表位-配置工厂
 * @Version: 1.0
 */
public class ShardingFactory {
    public static final List<ShardingElement> databaseList = new ArrayList<>();
    public static final List<ShardingElement> tableList = new ArrayList<>();
    private static final Random random = new Random();

    static {
        databaseList.add(new ShardingElement("0", 1));
        databaseList.add(new ShardingElement("1", 1));
        databaseList.add(new ShardingElement("2", 1));
    }

    static {
        tableList.add(new ShardingElement("0", 1));
        tableList.add(new ShardingElement("1", 1));
    }

    public static String getCode(List<ShardingElement> list) {
        int totalWeight = 0;
        for (ShardingElement element : list) {
            totalWeight += element.getWeight();
        }
        int currentWeight = 0;
        int randomCode = random.nextInt(totalWeight);
        for (ShardingElement element : list) {
            currentWeight += element.getWeight();
            if (randomCode < currentWeight) {
                return element.getName();
            }
        }
        throw new BizException(BizCodeEnum.FAIL);
    }
}
