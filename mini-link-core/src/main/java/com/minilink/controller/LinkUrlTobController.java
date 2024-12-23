package com.minilink.controller;

import com.minilink.enums.BizCodeEnum;
import com.minilink.pojo.LinkUrlTobVO;
import com.minilink.pojo.dto.LinkUrlSaveDTO;
import com.minilink.service.LinkUrlTobService;
import com.minilink.util.resp.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-03  16:37
 * @Description: B端-短链接
 * @Version: 1.0
 */
@Tag(name = "B端-短链接")
@RestController
@RequestMapping("/url")
public class LinkUrlTobController {
    @Autowired
    private LinkUrlTobService urlTobService;

    @Operation(summary = "创建链接")
    @PostMapping("/create")
    public R create(@Validated @RequestBody LinkUrlSaveDTO saveDTO) {
        urlTobService.createShortLink(saveDTO);
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "解析链接内容")
    @GetMapping("/parse")
    public R parse(String link) throws IOException {
        Map<String, Object> resultMap = urlTobService.parseLink(link);
        return R.out(BizCodeEnum.SUCCESS, resultMap);
    }

    @Operation(summary = "分页列表")
    @GetMapping("/page/{groupId}/{current}/{size}")
    public R getPageList(@PathVariable Long groupId,
                         @PathVariable Integer current,
                         @PathVariable Integer size) {
        Map<String, Object> resultMap = urlTobService.getPageList(groupId, current, size);
        return R.out(BizCodeEnum.SUCCESS, resultMap);
    }

    @Operation(summary = "链接详情")
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable Long id) {
        LinkUrlTobVO result = urlTobService.detail(id);
        return R.out(BizCodeEnum.SUCCESS, result);
    }

    @Operation(summary = "修改链接")
    @PostMapping("/update")
    public R update() {
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除链接")
    @DeleteMapping("/delete")
    public R delete() {
        return R.out(BizCodeEnum.SUCCESS);
    }
}
