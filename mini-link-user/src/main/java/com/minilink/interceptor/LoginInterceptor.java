package com.minilink.interceptor;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.pojo.po.MiniLinkUser;
import com.minilink.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 徐志斌
 * @Date: 2024/12/8 17:05
 * @Version 1.0
 * @Description: Token-拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static ThreadLocal threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        String token = request.getHeader("mini-link-token");
        if (ObjectUtils.isEmpty(token)) {
            throw new BizException(BizCodeEnum.ACCOUNT_NO_LOGIN);
        }
        MiniLinkUser userPO = JwtUtil.resolve(token);
        if (ObjectUtils.isEmpty(userPO)) {
            throw new BizException(BizCodeEnum.ACCOUNT_NO_LOGIN);
        }
        threadLocal.set(userPO);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        threadLocal.remove();
    }
}
