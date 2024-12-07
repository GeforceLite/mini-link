package com.minilink.controller;

import com.minilink.service.MiniLinkUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
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
@Tag(name = "账号协助")
@RestController
public class AssistController {
    @Autowired
    private MiniLinkUserService userService;

    @Operation(summary = "图片验证码")
    @GetMapping("/captcha")
    public void captcha() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.captcha();
    }

    @Operation(summary = "发送邮件")
    @PostMapping("/email/{email}")
    public void sendEmail(@PathVariable String email) throws MessagingException {
        userService.sendEmail(email);
    }
}
