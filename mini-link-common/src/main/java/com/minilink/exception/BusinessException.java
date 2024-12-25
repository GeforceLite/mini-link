package com.minilink.exception;

import com.minilink.enums.BusinessCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2024/12/4 10:52
 * @Version 1.0
 * @Description: 自定义业务异常
 */
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private BusinessCodeEnum codeEnum;
}