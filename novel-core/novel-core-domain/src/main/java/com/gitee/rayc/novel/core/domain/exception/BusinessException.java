package com.gitee.rayc.novel.core.domain.exception;

import com.gitee.rayc.novel.core.domain.enums.RespCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-28
 * @Description: 自定义业务异常
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private final RespCodeEnum respCodeEnum;

    public BusinessException(RespCodeEnum respCodeEnum) {
        // 不调用父类 Throwable的fillInStackTrace() 方法生成栈追踪信息，提高应用性能
        // 构造器之间的调用必须在第一行
        super(respCodeEnum.getMessage(), null, false, false);
        this.respCodeEnum = respCodeEnum;
    }

}
