package com.minilink.service;

import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.util.HttpServletUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-14
 */
public interface LinkUrlTocService {
    /**
     * 访问短链接
     *
     * @param shortLink 短链接
     */
    void redirect(String shortLink) throws IOException;
}
