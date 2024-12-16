package com.minilink.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.minilink.pojo.po.LinkUrlTob;
import com.minilink.service.LinkUrlTocService;
import com.minilink.store.LinkUrlTobStore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-13  13:42
 * @Description: C端-短链接
 * @Version: 1.0
 */
@Tag(name = "C端-短链接")
@Controller
public class LinkTocController {
    @Autowired
    private LinkUrlTocService tocService;
    @Autowired
    private LinkUrlTobStore tobStore;

    @Operation(summary = "访问短链接")
    @GetMapping("/{shortLink}")
    public void redirect(@PathVariable String shortLink) throws IOException {
        tocService.redirect(shortLink);
    }

    @ResponseBody
    @Operation(summary = "测试专用：为什么ToB也能根据short_link查询呢？")
    @GetMapping("/test2")
    public void test2(String shortLink) {
        QueryWrapper<LinkUrlTob> wrapper = new QueryWrapper<>();
        wrapper.eq("short_link", shortLink);
        List<LinkUrlTob> list = tobStore.list(wrapper);
        System.out.println(list.toString());
    }
}
