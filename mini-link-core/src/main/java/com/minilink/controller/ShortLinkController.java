package com.minilink.controller;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("生成短链接")
    @PostMapping("/createShortLink/{longLink}")
    public void createShort(@PathVariable String longLink) {

    }

    @ApiOperation("查询长链接")
    @GetMapping("/getLongLink/{shortLink}")
    public void getLongLink(@PathVariable String shortLink) {

    }

    @ApiOperation("链接跳转（短链接 ——> 原链接）")
    @GetMapping("/route/{shortLink}")
    public void route(@PathVariable String shortLink) {

    }
}
