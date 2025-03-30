package com.gitee.rayc.novel.core.api.dto.resp.user;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户信息 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
public class UserInfoRespDto {

    private Long id;

    private String username;

    /**
     * 昵称
     * */
    private String nickName;

    /**
     * 用户头像
     * */
    private String userPhoto;

    /**
     * 用户性别
     * */
    private Integer userSex;
}
