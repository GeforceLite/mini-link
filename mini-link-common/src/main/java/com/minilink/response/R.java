package com.minilink.response;

import com.minilink.enums.BizCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 21:59
 * @Version 1.0
 * @Description: 响应封装类
 */
@Getter
@Setter
public class R<T> extends BaseResponse {
    private T data;

    private R(BizCodeEnum respEnum) {
        super(respEnum);
    }

    private R(BizCodeEnum respEnum, T data) {
        super(respEnum);
        this.data = data;
    }

    public static <T> R<T> out(BizCodeEnum respEnum) {
        return new R<>(respEnum);
    }

    public static <T> R<T> out(BizCodeEnum respEnum, T data) {
        return new R<>(respEnum, data);
    }
}
