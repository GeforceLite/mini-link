package com.minilink.controller;

import com.minilink.enums.BizCodeEnum;
import com.minilink.util.resp.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-04  16:16
 * @Description: 链接分组
 * @Version: 1.0
 */
@Tag(name = "链接分组")
@RestController
@RequestMapping("/url/group")
public class LinkGroupController {
    @Operation(summary = "创建分组")
    @PostMapping("/create")
    public R createGroup() {
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "查询分组列表")
    @GetMapping("/list")
    public R getGroupList() {
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改分组")
    @PostMapping("/update")
    public R updateGroup() {
        return R.out(BizCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除分组")
    @DeleteMapping("/delete/{groupId}")
    public R deleteGroup(@PathVariable String groupId) {
        return R.out(BizCodeEnum.SUCCESS);
    }
}
