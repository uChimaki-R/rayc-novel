package com.gitee.rayc.novel.core.domain.constant;


import lombok.Getter;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-27
 * @Description: 通用常量
 * @Version: 1.0
 */
public class CommonConsts {

    /**
     * 是
     */
    public static final Integer YES = 1;
    public static final String TRUE = "true";


    /**
     * 否
     */
    public static final Integer NO = 0;
    public static final String FALSE = "false";


    /**
     * 常量类实例化异常信息
     */
    public static final String CONST_INSTANCE_EXCEPTION_MSG = "Constant class";

    /**
     * 性别常量
     */
    @Getter
    public enum SexEnum {

        /**
         * 男
         */
        MALE(0, "男"),

        /**
         * 女
         */
        FEMALE(1, "女");

        SexEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private final int code;
        private final String desc;

    }
}
