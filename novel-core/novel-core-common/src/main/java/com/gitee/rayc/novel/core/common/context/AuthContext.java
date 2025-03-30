package com.gitee.rayc.novel.core.common.context;

import lombok.experimental.UtilityClass;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-28
 * @Description: 用户信息上下文
 * @Version: 1.0
 */
@UtilityClass
public class AuthContext {

    /**
     * 当前线程用户ID
     */
    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    /**
     * 当前线程作家ID
     */
    private static final ThreadLocal<Long> AUTHOR_ID = new ThreadLocal<>();

    public void setUserId(Long userId) {
        USER_ID.set(userId);
    }

    public Long getUserId() {
        return USER_ID.get();
    }

    public void setAuthorId(Long authorId) {
        AUTHOR_ID.set(authorId);
    }

    public Long getAuthorId() {
        return AUTHOR_ID.get();
    }

    public void clear() {
        USER_ID.remove();
        AUTHOR_ID.remove();
    }

}
