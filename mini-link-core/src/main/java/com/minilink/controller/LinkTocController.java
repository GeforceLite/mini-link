package com.minilink.controller;

import com.minilink.service.LinkUrlTocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  13:42
 * @Description: C端-短链接
 * @Version: 1.0
 */
@Tag(name = "C端-短链接")
@RestController
public class LinkTocController {
    @Autowired
    private LinkUrlTocService tocService;

    @Operation(summary = "访问短链接")
    @GetMapping("/{shortLink}")
    public void redirect(@PathVariable String shortLink) throws IOException {
        tocService.redirect(shortLink);
    }
}
