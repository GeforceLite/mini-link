package com.minilink.controller;

import com.minilink.enums.BizCodeEnum;
import com.minilink.response.R;
import com.minilink.service.UserAssistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 21:35
 * @Version 1.0
 * @Description: 账号辅助相关控制器
 */
@Tag(name = "账号辅助")
@RestController
public class UserAssistController {
    @Autowired
    private UserAssistService assistService;

    @Operation(summary = "图片验证码")
    @GetMapping("/captcha")
    public void captcha() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        assistService.captcha();
    }

    @Operation(summary = "发送邮件")
    @PostMapping("/email/{email}")
    public R sendEmail(@PathVariable String email) throws MessagingException {
        assistService.sendEmail(email);
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "上传头像")
    @PostMapping("/avatar")
    public R uploadAvatar(MultipartFile avatarFile) {
        assistService.uploadAvatar(avatarFile);
        return R.out(BizCodeEnum.SUCCESS);
    }
}