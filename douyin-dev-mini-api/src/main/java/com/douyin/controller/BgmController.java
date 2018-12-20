package com.douyin.controller;

import com.douyin.serivce.BgmService;
import com.douyin.utils.IDouyinJSONResult;
import com.douyin.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "背景音乐相关的接口", tags = {"背景音乐相关的Controller"})
@RequestMapping("/bgm")
public class BgmController {

    @Autowired
    private BgmService bgmService;

    @ApiOperation(value = "请求BGM列表", notes = "请求BGM列表的接口")
    @PostMapping("/list")
    public IDouyinJSONResult list() {
        return IDouyinJSONResult.ok(bgmService.queryBgmList());
    }
}
