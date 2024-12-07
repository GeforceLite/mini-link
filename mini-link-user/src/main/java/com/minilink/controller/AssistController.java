package com.minilink.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.minilink.constant.RedisConstant;
import com.minilink.service.MiniLinkUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private MiniLinkUserService userService;

    @ApiOperation(value = "图片验证码")
    @GetMapping("/captcha")
    public void captcha() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.captcha();
    }

    @ApiOperation(value = "发送邮件")
    @PostMapping("/email/{email}")
    public void sendEmail(@PathVariable String email) {
        userService.sendEmail(email);
    }
}
