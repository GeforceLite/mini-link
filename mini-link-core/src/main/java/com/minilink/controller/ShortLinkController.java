package com.minilink.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-03  16:37
 * @Description: 短链接
 * @Version: 1.0
 */
@RestController
@RequestMapping("/link")
public class ShortLinkController {
    @PostMapping("/createShortLink/{longLink}")
    public void createShort(@PathVariable String longLink) {

    }

    @GetMapping("/getLongLink/{shortLink}")
    public void getLongLink(@PathVariable String shortLink) {

    }

    @GetMapping("/route/{shortLink}")
    public void route(@PathVariable String shortLink) {

    }
}
