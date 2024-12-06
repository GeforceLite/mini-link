package com.minilink.controller;

import com.minilink.service.MiniLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2024-12-06
 */
@RestController
public class MiniLinkUserController {
    @Autowired
    private MiniLinkUserService userService;

    @RequestMapping("/test")
    public String test() {
        return "SUCCESS";
    }
}
