package com.minilink.util.resp;

import com.minilink.enums.BizCodeEnum;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 21:59
 * @Version 1.0
 * @Description: 响应封装类
 */
public class R<T> extends BaseResponse {
    private T data;

    private R(BizCodeEnum codeEnum) {
        super(codeEnum);
    }

    private R(BizCodeEnum codeEnum, T data) {
        super(codeEnum);
        this.data = data;
    }

    public static <T> R<T> out(BizCodeEnum codeEnum) {
        return new R<>(codeEnum);
    }

    public static <T> R<T> out(BizCodeEnum codeEnum, T data) {
        return new R<>(codeEnum, data);
    }
}
