package com.minilink.util;

import java.util.Random;

/**
 * @Author 徐志斌
 * @Date: 2023/11/25 15:02
 * @Version 1.0
 * @Description: 随机生成工具类
 */
public class RandomUtil {
    public static final String NUM = "0123456789";
    public static final String TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String NUM_TEXT = NUM + TEXT;

    /**
     * 生成随机内容
     *
     * @param length 内容长度
     * @param type   内容类型（1：数字，2：字母，3：数字 + 字母）
     * @return 生成内容
     */
    public static String generate(int length, int type) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        String charSet = "";
        switch (type) {
            case 1:
                charSet = NUM;
                break;
            case 2:
                charSet = TEXT;
                break;
            case 3:
                charSet = NUM_TEXT;
                break;
            default:
                return "Invalid type";
        }
        for (int i = 0; i < length; i++) {
            code.append(charSet.charAt(random.nextInt(charSet.length())));
        }
        return code.toString();
    }
}
