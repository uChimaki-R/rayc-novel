package com.gitee.rayc.novel.core.api.dto.req.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户信息更新 请求DTO
 * @Version: 1.0
 */
@Data
public class UserInfoUptReqDto {

    private Long userId;

    @Length(min = 2, max = 10)
    private String nickName;

    private String userPhoto;

    @Min(value = 0)
    @Max(value = 1)
    private Integer userSex;

}
