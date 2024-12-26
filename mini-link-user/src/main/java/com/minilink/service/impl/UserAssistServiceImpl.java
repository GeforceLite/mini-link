package com.minilink.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Producer;
import com.minilink.constant.RedisConstant;
import com.minilink.constant.RegexConstant;
import com.minilink.enums.BusinessCodeEnum;
import com.minilink.exception.BusinessException;
import com.minilink.pojo.entity.EmailParamEntity;
import com.minilink.service.UserAssistService;
import com.minilink.strategy.email.AbstractEmailStrategy;
import com.minilink.strategy.email.EmailStrategyFactory;
import com.minilink.util.EncryptUtil;
import com.minilink.util.HttpServletUtil;
import com.minilink.util.IpUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class UserAssistServiceImpl implements UserAssistService {
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void captcha() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String captchaText = captchaProducer.createText();
        String captchaKey = this.getCaptchaKey();
        redisTemplate.opsForValue().set(captchaKey, captchaText, 3, TimeUnit.MINUTES);
        HttpServletResponse response = HttpServletUtil.getResponse();
        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error("----------图片验证码生成失败:{}----------", e.getMessage());
        }
    }

    @Override
    public String getCaptchaKey() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        HttpServletRequest request = HttpServletUtil.getRequest();
        String userAgent = request.getHeader("User-Agent");
        return RedisConstant.CAPTCHA_KEY + EncryptUtil.md5(userAgent + IpUtil.getIpAddr());
    }

    @Override
    public void sendEmail(Integer type, String email) {
        if (!email.matches(RegexConstant.REGEX_EMAIL_FORMAT)) {
            throw new BusinessException(BusinessCodeEnum.CODE_EMAIL_ERROR);
        }
        String emailCheckKey = (String) redisTemplate.opsForValue().get(RedisConstant.EMAIL_CHECK_KEY + email);
        if (StringUtils.isNotBlank(emailCheckKey)) {
            throw new BusinessException(BusinessCodeEnum.OPS_REPEAT);
        }
        EmailParamEntity paramEntity = new EmailParamEntity();
        paramEntity.setEmail(email);
        AbstractEmailStrategy emailHandler = EmailStrategyFactory.getStrategyHandler(type);
        emailHandler.sendEmail(paramEntity);
    }

    @Override
    public void uploadFile(Integer type, MultipartFile file) {

    }
}
