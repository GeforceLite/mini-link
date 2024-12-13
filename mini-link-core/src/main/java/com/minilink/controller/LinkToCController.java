package com.minilink.controller;

import com.minilink.service.LinkUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  13:42
 * @Description: C端-短链接
 * @Version: 1.0
 */
@Tag(name = "C端-短链接")
@Controller
@RequestMapping("/toc")
public class LinkToCController {
    @Autowired
    private LinkUrlService urlService;

    @Operation(summary = "访问短链接")
    @GetMapping("/redirect/{shortLink}")
    public void redirect(@PathVariable String shortLink) throws IOException {
        urlService.redirect(shortLink);
//        return R.out(BizCodeEnum.SUCCESS);
    }
}
