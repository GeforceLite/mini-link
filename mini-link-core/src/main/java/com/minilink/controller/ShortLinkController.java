package com.minilink.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-03  16:37
 * @Description: 短链接
 * @Version: 1.0
 */
@RestController
@RequestMapping("/link")
public class ShortLinkController {
    /**
     * 生成短链接
     */
    @PostMapping("/generateShortUrl")
    public void generateShortLink() {

    }

    /**
     * 查询长链接
     */
    @GetMapping("/getLongUrl")
    public void getLongUrl() {

    }
}
