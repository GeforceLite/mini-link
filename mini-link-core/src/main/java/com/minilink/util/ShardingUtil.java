package com.minilink.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  10:44
 * @Description: 库位、表位工具类
 * @Version: 1.0
 * --------------------------------------
 * TODO 数据倾斜，配置权重
 */
public class ShardingUtil {
    private static final List<String> databaseList = new ArrayList<>();
    private static final List<String> tableList = new ArrayList<>();
    private static final Random random = new Random();

    static {
        databaseList.add("0");
        databaseList.add("1");
        databaseList.add("2");
        // 可持续扩容......
    }

    static {
        tableList.add("0");
        tableList.add("1");
        // 可持续扩容......
    }

    public static String getDatabaseCode() {
        int code = random.nextInt(databaseList.size());
        return databaseList.get(code);
    }

    public static String getTableCode() {
        int code = random.nextInt(tableList.size());
        return tableList.get(code);
    }
}
