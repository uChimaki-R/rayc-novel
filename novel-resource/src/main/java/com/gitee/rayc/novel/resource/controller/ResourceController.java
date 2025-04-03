package com.gitee.rayc.novel.resource.controller;

import com.gitee.rayc.novel.core.domain.constant.ApiRouterConsts;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.resource.entity.ImgVerifyCode;
import com.gitee.rayc.novel.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 前台门户-资源(图片/视频/文档)模块 API 控制器
 * @Version: 1.0
 */
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_RESOURCE_URL_PREFIX)
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    /**
     * 获取图片验证码接口
     */
    @GetMapping("img_verify_code")
    public RestResp<ImgVerifyCode> getImgVerifyCode() throws IOException {
        return resourceService.getImgVerifyCode();
    }

    /**
     * 图片上传接口
     */
    @PostMapping("/image")
    RestResp<String> uploadImage(@RequestParam("file") MultipartFile file) {
        return resourceService.uploadImage(file);
    }

}
