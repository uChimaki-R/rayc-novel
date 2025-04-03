package com.gitee.rayc.novel.resource.service;

import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.resource.entity.ImgVerifyCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 资源（图片/视频/文档）相关服务类
 * @Version: 1.0
 */
public interface ResourceService {

    /**
     * 获取图片验证码
     *
     * @throws IOException 验证码图片生成失败
     * @return Base64编码的图片
     */
    RestResp<ImgVerifyCode> getImgVerifyCode() throws IOException;

    /**
     * 图片上传
     * @param file 需要上传的图片
     * @return 图片访问路径
     * */
    RestResp<String> uploadImage(MultipartFile file);
}
