package com.minilink.controller;

import com.google.code.kaptcha.Producer;
import com.minilink.constant.RedisConstant;
import com.minilink.util.HttpServletUtil;
import com.minilink.util.IpUtil;
import com.minilink.util.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
@Slf4j
@Api(tags = "账号协助")
@RestController
public class AssistController {
    @Autowired
    private Producer captchaProducer;

    @ApiOperation(value = "图片验证码")
    @GetMapping("/captcha")
    public void captcha() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String captchaText = captchaProducer.createText();
        HttpServletRequest request = HttpServletUtil.getRequest();
        String userAgent = request.getHeader("User-Agent");
        String ip = IpUtil.getIpAddr(request);
        String captchaKey = RedisConstant.CAPTCHA_KEY + Md5Util.encrypt(userAgent + ip);

        // TODO 验证码信息放入缓存

        HttpServletResponse response = HttpServletUtil.getResponse();
        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error("IOException:{}", e.getMessage());
        }
    }
}
