package com.minilink.util;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-27  11:38
 * @Description: OKHTTP 工具类
 * @Version: 1.0
 */
@Slf4j
public class OkHttpUtil {
    public static final String MEDIA_TYPE_JSON = "application/json; charset=utf-8";

    public static OkHttpClient getOkHttpClient() {
        return getOkHttpClient(60, 60, 60);
    }

    public static OkHttpClient getOkHttpClient(int connectTimeout, int readTimeOut, int writeTimeOut) {
        OkHttpClient.Builder builder = new okhttp3.OkHttpClient().newBuilder();
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        builder.readTimeout(readTimeOut, TimeUnit.SECONDS);
        builder.writeTimeout(writeTimeOut, TimeUnit.SECONDS);
        return builder.build();
    }

    public static String get(OkHttpClient okHttpClient, String url, Headers headers) {
        log.info("okHttpClient get url:{}.", url);
        Request request = new Request.Builder().url(url).headers(headers).get().build();

        String responseData = request(okHttpClient, url, request);
        log.info("okHttpClient get url:{},request responseData====> {}", url, responseData);
        return responseData;
    }

    public static String get(OkHttpClient okHttpClient, String url) {
        Headers headers = new Headers.Builder().build();
        return get(okHttpClient, url, headers);
    }

    public static String get(String url) {
        OkHttpClient okHttpClient = getOkHttpClient();
        Headers headers = new Headers.Builder().build();
        return get(okHttpClient, url, headers);
    }

    public static String post(OkHttpClient okHttpClient, String url, JSONObject bodyJson, Headers headers) {
        log.info("okHttpClient post url:{}, body====> {}", url, bodyJson);
        MediaType mediaTypeJson = MediaType.parse(MEDIA_TYPE_JSON);
        RequestBody requestBody = RequestBody.create(mediaTypeJson, bodyJson.toString());
        Request request = new Request.Builder().url(url).headers(headers).post(requestBody).build();

        String responseData = request(okHttpClient, url, request);
        log.info("okHttpClient post url:{},post responseData====> {}", url, responseData);
        return responseData;
    }

    public static String post(OkHttpClient okHttpClient, String url, JSONObject bodyJson) {
        Headers headers = new Headers.Builder().build();
        return post(okHttpClient, url, bodyJson, headers);
    }

    public static String post(String url, JSONObject bodyJson) {
        OkHttpClient okHttpClient = getOkHttpClient();
        Headers headers = new Headers.Builder().build();
        return post(okHttpClient, url, bodyJson, headers);
    }

    public static String request(OkHttpClient okHttpClient, String url, Request request) {
        String responseData = "";
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response != null && response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okHttpClient getResponse error.url:{}", url, e);
        }
        return responseData;
    }
}
