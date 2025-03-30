package com.gitee.rayc.novel.core.api.dto.entity.user;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户信息 DTO
 * @Version: 1.0
 */
@Data
@Builder
public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer status;

}
