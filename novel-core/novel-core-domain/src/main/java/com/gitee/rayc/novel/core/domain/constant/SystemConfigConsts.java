package com.gitee.rayc.novel.core.domain.constant;

import static com.gitee.rayc.novel.core.domain.constant.CommonConsts.CONST_INSTANCE_EXCEPTION_MSG;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-27
 * @Description: 系统配置常量
 * @Version: 1.0
 */
public class SystemConfigConsts {

    private SystemConfigConsts() {
        throw new IllegalStateException(CONST_INSTANCE_EXCEPTION_MSG);
    }

    /**
     * Http 请求认证 Header
     */
    public static final String HTTP_AUTH_HEADER_NAME = "Authorization";

    /**
     * 前台门户系统标识
     */
    public static final String NOVEL_FRONT_KEY = "front";

    /**
     * 作家管理系统标识
     */
    public static final String NOVEL_AUTHOR_KEY = "author";

    /**
     * 后台管理系统标识
     */
    public static final String NOVEL_ADMIN_KEY = "admin";

    /**
     * 图片上传目录
     */
    public static final String IMAGE_UPLOAD_DIRECTORY = "/image/";

}
