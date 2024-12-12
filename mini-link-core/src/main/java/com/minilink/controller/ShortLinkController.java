package com.minilink.controller;

import com.minilink.pojo.po.MiniLinkUrl;
import com.minilink.store.MiniLinkUrlStore;
import com.minilink.util.RandomUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-03  16:37
 * @Description: 短链接
 * @Version: 1.0
 */
@Tag(name = "短链接相关")
@RestController
@RequestMapping("/link")
public class ShortLinkController {
    @Autowired
    private MiniLinkUrlStore urlService;

    /**
     * 不能使用301，担心永久重定向由于浏览器缓存问题，就无法统计pv uv数据了
     * 这里必须使用302临时重定向，虽然会增大服务器压力，但是数据统计是极其重要的
     * @param longLink
     */
    @Operation(summary = "生成短链接")
    @PostMapping("/createLong/{longLink}")
    public void createShort(@PathVariable String longLink) {
        for (int i = 0; i < 10; i++) {
            MiniLinkUrl url = new MiniLinkUrl();
            url.setShortLink(RandomUtil.generate(8, 2));
            url.setAccountId("xuzhibin");
            url.setGroupId(123219939L);
            url.setLongLink(longLink);
            urlService.save(url);
        }
    }

    @Operation(summary = "查询长链接")
    @GetMapping("/getLong/{shortLink}")
    public void getLongLink(@PathVariable String shortLink) {

    }

    @Operation(summary = "重定向跳转长连接")
    @GetMapping("/redirect/{shortLink}")
    public void route(@PathVariable String shortLink) {

    }
}
