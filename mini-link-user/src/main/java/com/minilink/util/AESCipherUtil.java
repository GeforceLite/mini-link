package com.minilink.util;

import com.minilink.util.properties.SecurityProperties;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * AES加密工具类
 *
 * @author : Barry.chen
 * @date : 2024-12-28
 **/
@Component
public class AESCipherUtil implements ApplicationContextAware {

    private static byte[] key;

    private static IvParameterSpec iv;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SecurityProperties properties = applicationContext.getBean(SecurityProperties.class);
        try {
            key = properties.getAes().getKey().getBytes(StandardCharsets.UTF_8);
            iv = new IvParameterSpec(properties.getAes().getIv().getBytes(StandardCharsets.UTF_8));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密
     *
     * @param ciphertext 加密前
     * @return 加密后文字
     * @throws Exception IllegalArgumentException NullPointerException NoSuchAlgorithmException NoSuchPaddingException k
     */
    public static String decrypt(final String ciphertext) throws Exception {
        // Base64编码，避免出现其他字符
        final byte[] base64Decode = Base64.getDecoder().decode(ciphertext);

        // AES(Rijndael)加密
        final SecretKeySpec sks = new SecretKeySpec(key, "AES");
        final Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cip.init(Cipher.DECRYPT_MODE, sks, iv);

        // 加密后逆向成String
        return new String(cip.doFinal(base64Decode), StandardCharsets.UTF_8);
    }

    /**
     * 解密
     *
     * @param ciphertext 加密后文字
     * @return 解密文字
     * @throws Exception
     */
    public static String encrypt(final String ciphertext) throws Exception {
        // AES(Rijndael)解密
        final SecretKeySpec sks = new SecretKeySpec(key, "AES");
        final Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cip.init(Cipher.ENCRYPT_MODE, sks, iv);
        final byte[] cipher_text = cip.doFinal(ciphertext.getBytes(StandardCharsets.UTF_8));

        // Base64转换
        final String str_text = Base64.getEncoder().encodeToString(cipher_text);

        return str_text;
    }

}