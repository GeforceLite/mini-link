package com.minilink.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  10:44
 * @Description: 库表位计算工具类：为了避免后续扩容后数据迁移场景
 * @Version: 1.0
 */
public class ShardingUtil {
    private static final List<String> databaseList = new ArrayList<>();
    private static final List<String> tableList = new ArrayList<>();
    private static final Random random = new Random();

    static {
        databaseList.add("0");
        databaseList.add("1");
        databaseList.add("2");
    }

    static {
        tableList.add("0");
        tableList.add("1");
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
