package com.gitee.rayc.novel.core.data.anno;

import com.gitee.rayc.novel.core.domain.enums.RespCodeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 分布式 Lock 注解
 * @Version: 1.0
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Lock {

    String prefix();

    boolean isWait() default false;

    long waitTime() default 3L;

    RespCodeEnum failCode() default RespCodeEnum.OK;

}
