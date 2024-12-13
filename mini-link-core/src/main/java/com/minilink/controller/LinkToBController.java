package com.minilink.controller;

import com.minilink.enums.BizCodeEnum;
import com.minilink.pojo.dto.LinkUrlSaveDTO;
import com.minilink.service.LinkUrlService;
import com.minilink.util.resp.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-03  16:37
 * @Description: B端-短链接
 * @Version: 1.0
 */
@Tag(name = "B端-短链接")
@RestController
@RequestMapping("/tob")
public class LinkToBController {
    @Autowired
    private LinkUrlService urlService;

    @Operation(summary = "创建链接")
    @PostMapping("/create")
    public R create(@RequestBody LinkUrlSaveDTO saveDTO) {
        urlService.createShortLink(saveDTO);
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "分页列表")
    @GetMapping("/list")
    public R list() {
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "链接详情")
    @GetMapping("/detail")
    public R detail() {
        return R.out(BizCodeEnum.SUCCESS);
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
