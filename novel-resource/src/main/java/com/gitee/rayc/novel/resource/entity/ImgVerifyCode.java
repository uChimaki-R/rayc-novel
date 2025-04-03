package com.gitee.rayc.novel.resource.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 图像验证码
 * @Version: 1.0
 */
@Data
@Builder
public class ImgVerifyCode {

    /**
     * 当前会话ID，用于标识改图形验证码属于哪个会话
     */
    private String sessionId;

    /**
     * Base64 编码的验证码图片
     */
    private String img;

}
