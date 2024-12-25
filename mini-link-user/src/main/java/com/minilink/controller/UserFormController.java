package com.minilink.controller;

import com.minilink.annotation.NoLogin;
import com.minilink.enums.BusinessCodeEnum;
import com.minilink.pojo.dto.LoginDTO;
import com.minilink.pojo.dto.RegisterDTO;
import com.minilink.service.UserFormService;
import com.minilink.util.resp.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 21:35
 * @Version 1.0
 * @Description: 账号表单相关控制器
 */
@Tag(name = "账号表单")
@RestController
public class UserFormController {
    @Autowired
    private UserFormService formService;

    @NoLogin
    @Operation(summary = "注册账号")
    @PostMapping("/register")
    public R register(@RequestBody RegisterDTO registerDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        formService.register(registerDTO);
        return R.out(BusinessCodeEnum.SUCCESS);
    }

    @NoLogin
    @Operation(summary = "登录账号")
    @PostMapping("/login")
    public R login(@RequestBody LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> resultMap = formService.login(loginDTO);
        return R.out(BusinessCodeEnum.SUCCESS, resultMap);
    }

    @Operation(summary = "修改密码")
    @PostMapping("/updatePwd")
    public R forget() {
        return R.out(BusinessCodeEnum.SUCCESS);
    }

    @NoLogin
    @Operation(summary = "找回密码")
    @PostMapping("/find")
    public R findBack() {
        return R.out(BusinessCodeEnum.SUCCESS);
    }
}
