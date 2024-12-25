package com.minilink.util.resp;

import com.minilink.enums.BusinessCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 21:59
 * @Version 1.0
 * @Description: 响应基础类
 */
@Getter
@Setter
public class BaseResponse {
    private Integer code;
    private String msg;

    protected BaseResponse(BusinessCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }
}