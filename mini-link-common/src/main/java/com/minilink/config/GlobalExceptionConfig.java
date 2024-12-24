package com.minilink.config;

import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.util.resp.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 11:08
 * @Version 1.0
 * @Description: 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionConfig {
    /**
     * 自定义异常 BizException
     */
    @ExceptionHandler(BizException.class)
    public R bizException(BizException e) {
        log.error("-------------bizException:{}-------------", e.getCodeEnum());
        return R.out(e.getCodeEnum());
    }

    /**
     * 服务端接口参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public R validationException(BindException e) {
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append("\r\n"));
        log.error("-------------bindException:{}-------------", e.getMessage());
        return R.out(BizCodeEnum.PARAM_ERROR, sb);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public R handlerMethodValidationException(HandlerMethodValidationException e) {
        log.error("-------------handlerMethodValidationException:{}-------------", e.getMessage());
        return R.out(BizCodeEnum.REGEX_SHORT_LINK_FORMAT_ERROR, e.getMessage());
    }


    /**
     * 异常兜底 Exception
     */
    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        log.error("-------------exception:{}-------------", e.getMessage());
        return R.out(BizCodeEnum.FAIL, e.getMessage());
    }
}