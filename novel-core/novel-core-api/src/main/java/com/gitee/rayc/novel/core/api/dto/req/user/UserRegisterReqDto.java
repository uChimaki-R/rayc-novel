package com.gitee.rayc.novel.core.api.dto.req.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户注册 请求DTO
 * @Version: 1.0
 */
@Data
public class UserRegisterReqDto {

    @NotBlank(message="手机号不能为空！")
    @Pattern(regexp="^1[3|4|5|6|7|8|9][0-9]{9}$",message="手机号格式不正确！")
    private String username;

    @NotBlank(message="密码不能为空！")
    private String password;

    @NotBlank(message="验证码不能为空！")
    @Pattern(regexp="^\\d{4}$",message="验证码格式不正确！")
    private String velCode;

    /**
     * 请求会话标识，用来标识图形验证码属于哪个会话
     * */
    @NotBlank
    @Length(min = 32,max = 32)
    private String sessionId;

}
