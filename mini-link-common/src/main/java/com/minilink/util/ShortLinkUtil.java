package com.minilink.util;

import com.google.common.hash.Hashing;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-12  13:12
 * @Description: 短链接工具类：Murmurhash算法 + Base62编码
 * @Version: 1.0
 */
public class ShortLinkUtil {
    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generate(String link) {
        long murmurHash32 = murmurHash32(link);
        return encodeToBase62(murmurHash32);
    }

    private static long murmurHash32(String str) {
        return Hashing.murmur3_32_fixed().hashUnencodedChars(str).padToLong();
    }

    private static String encodeToBase62(long num) {
        StringBuffer sb = new StringBuffer();
        do {
            int i = (int) (num % CHARS.length());
            sb.append(CHARS.charAt(i));
            num = num / CHARS.length();
        } while (num > 0);
        return sb.reverse().toString();
    }
}
