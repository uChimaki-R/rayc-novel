package com.gitee.rayc.novel.core.domain.resp;

import com.gitee.rayc.novel.core.domain.enums.RespCodeEnum;
import lombok.Getter;

import java.util.Objects;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-27
 * @Description: 后端统一响应数据格式模板
 * @Version: 1.0
 */
@Getter
public class RestResp<T> {

    /**
     * 响应码
     */
    private final String code;

    /**
     * 响应消息
     */
    private final String message;

    /**
     * 响应数据
     */
    private T data;

    private RestResp() {
        this.code = RespCodeEnum.OK.getCode();
        this.message = RespCodeEnum.OK.getMessage();
    }

    private RestResp(RespCodeEnum respCodeEnum) {
        this.code = respCodeEnum.getCode();
        this.message = respCodeEnum.getMessage();
    }

    private RestResp(T data) {
        this();
        this.data = data;
    }

    /**
     * 业务处理成功,无数据返回
     */
    public static RestResp<Void> ok() {
        return new RestResp<>();
    }

    /**
     * 业务处理成功，有数据返回
     */
    public static <T> RestResp<T> ok(T data) {
        return new RestResp<>(data);
    }

    /**
     * 业务处理失败
     */
    public static RestResp<Void> fail(RespCodeEnum respCodeEnum) {
        return new RestResp<>(respCodeEnum);
    }

    /**
     * 系统错误
     */
    public static RestResp<Void> error() {
        return new RestResp<>(RespCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 判断是否成功
     */
    public boolean isOk() {
        return Objects.equals(this.code, RespCodeEnum.OK.getCode());
    }

}
