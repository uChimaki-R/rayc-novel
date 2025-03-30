package com.gitee.rayc.novel.core.api.dto.resp.user;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户注册 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
public class UserRegisterRespDto {

    private Long uid;

    private String token;
}
