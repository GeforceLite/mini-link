package com.minilink.exception;

import com.minilink.enums.BizCodeEnum;
import com.minilink.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 11:08
 * @Version 1.0
 * @Description: 全局统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义异常 BizException
     */
    @ExceptionHandler(BizException.class)
    public R bizException(BizException e) {
        return R.out(e.getCodeEnum());
    }

    /**
     * 所有异常兜底
     */
    @ExceptionHandler(Exception.class)
    public R bindException(Exception e) {
        return R.out(BizCodeEnum.FAIL, e.getMessage());
    }

//    /**
//     * 注解校验异常 BindException
//     */
//    @ExceptionHandler(BindException.class)
//    public R validationException(BindException b) {
//        final StringBuilder sb = new StringBuilder();
//        b.getBindingResult().getAllErrors().forEach(e -> sb.append(e.getDefaultMessage()).append("\r\n"));
//        return R.out(ResponseEnum.FAIL, sb);
//    }
}