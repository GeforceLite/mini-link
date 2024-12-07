package com.minilink.controller;

import com.minilink.enums.BizCodeEnum;
import com.minilink.response.R;
import com.minilink.service.UserAssistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 21:35
 * @Version 1.0
 * @Description: 账号信息相关控制器
 */
@Tag(name = "账号信息")
@RestController
public class UserInfoController {
    @Autowired
    private UserAssistService userService;

    @Operation(summary = "查询账户信息")
    @GetMapping("/info")
    public R register() {
//        userService.register();
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改用户信息")
    @PostMapping("/update")
    public R login() {
//        userService.login();
        return R.out(BizCodeEnum.SUCCESS);
    }
}
