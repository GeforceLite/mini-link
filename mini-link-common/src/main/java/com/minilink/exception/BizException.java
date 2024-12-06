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
    private BizCodeEnum codeEnum;
}