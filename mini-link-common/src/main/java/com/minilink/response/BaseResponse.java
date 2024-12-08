package com.minilink.response;

import com.minilink.enums.BizCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 21:59
 * @Version 1.0
 * @Description: 响应类
 */
@Getter
@Setter
public class BaseResponse {
    private Integer code;
    private String msg;

    protected BaseResponse(BizCodeEnum code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}