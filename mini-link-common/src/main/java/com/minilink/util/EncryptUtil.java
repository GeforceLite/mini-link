package com.minilink.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 21:09
 * @Version 1.0
 * @Description: 加密工具类
 */
public class EncryptUtil {
    public static String md5(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }
}
