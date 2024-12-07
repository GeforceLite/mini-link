package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Producer;
import com.minilink.constant.RedisConstant;
import com.minilink.enums.BizCodeEnum;
import com.minilink.exception.BizException;
import com.minilink.service.MiniLinkUserService;
import com.minilink.util.HttpServletUtil;
import com.minilink.util.IpUtil;
import com.minilink.util.Md5Util;
import com.minilink.util.RandomCodeUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
@Slf4j
@Service
public class MiniLinkUserServiceImpl implements MiniLinkUserService {
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void captcha() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String captchaText = captchaProducer.createText();
        HttpServletRequest request = HttpServletUtil.getRequest();
        String userAgent = request.getHeader("User-Agent");
        String ip = IpUtil.getIpAddr(request);
        String captchaKey = RedisConstant.CAPTCHA_KEY + Md5Util.encrypt(userAgent + ip);
        redisTemplate.opsForValue().set(captchaKey, captchaText, 3, TimeUnit.MINUTES);
        HttpServletResponse response = HttpServletUtil.getResponse();
        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error("图片验证码生成失败:{}", e.getMessage());
        }
    }

    @Override
    public void sendEmail(String email) {
        // 邮件发送按钮 60s 防刷
        String emailCheckKey = (String) redisTemplate.opsForValue().get(RedisConstant.EMAIL_CHECK_KEY + email);
        if (!StringUtils.isBlank(emailCheckKey)) {
            throw new BizException(BizCodeEnum.OPS_REPEAT);
        }

        String checkKey = RedisConstant.EMAIL_CHECK_KEY + email;
        redisTemplate.opsForValue().set(checkKey, "email send check", 60, TimeUnit.SECONDS);

        // 发送邮件，缓存验证码
        String code = RandomCodeUtil.generate(4, 1);

    }
}
