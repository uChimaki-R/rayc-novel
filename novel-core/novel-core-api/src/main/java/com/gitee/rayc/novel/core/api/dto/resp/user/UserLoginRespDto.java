package com.gitee.rayc.novel.core.api.dto.resp.user;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户登录 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
public class UserLoginRespDto {

    private Long uid;

    private String nickName;

    private String token;
}
