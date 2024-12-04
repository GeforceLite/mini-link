package com.minilink.exception;

import com.minilink.enums.BizCodeEnum;
import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2024/12/4 10:52
 * @Version 1.0
 * @Description: 自定义异常
 */
@Data
public class BizException extends RuntimeException {
    private Integer code;
    private String msg;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }
}