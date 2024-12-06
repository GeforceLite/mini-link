package com.minilink.util.resp;

import java.util.Random;

/**
 * @Author 徐志斌
 * @Date: 2023/11/25 15:02
 * @Version 1.0
 * @Description: 随机生成工具类
 */
public class RandomCodeUtil {
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS_AND_LETTERS = NUMBERS + LETTERS;

    /**
     * 生成随机内容
     *
     * @param length 内容长度
     * @param type   内容类型（1：数字，2：字母，3：数字 + 字母）
     * @return 生成内容
     */
    public static String generate(int length, int type) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String charSet = "";
        switch (type) {
            case 1:
                charSet = NUMBERS;
                break;
            case 2:
                charSet = LETTERS;
                break;
            case 3:
                charSet = NUMBERS_AND_LETTERS;
                break;
            default:
                return "Invalid type";
        }
        for (int i = 0; i < length; i++) {
            sb.append(charSet.charAt(random.nextInt(charSet.length())));
        }
        return sb.toString();
    }
}
