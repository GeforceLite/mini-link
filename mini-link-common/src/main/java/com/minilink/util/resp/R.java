package com.minilink.util.resp;

import com.minilink.enums.BusinessCodeEnum;
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

    private R(BusinessCodeEnum codeEnum) {
        super(codeEnum);
    }

    private R(BusinessCodeEnum codeEnum, T data) {
        super(codeEnum);
        this.data = data;
    }

    public static <T> R<T> out(BusinessCodeEnum codeEnum) {
        return new R<>(codeEnum);
    }

    public static <T> R<T> out(BusinessCodeEnum codeEnum, T data) {
        return new R<>(codeEnum, data);
    }
}
