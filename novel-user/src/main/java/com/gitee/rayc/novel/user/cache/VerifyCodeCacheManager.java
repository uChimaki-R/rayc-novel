package com.gitee.rayc.novel.user.cache;

import com.gitee.rayc.novel.core.data.constant.CacheConsts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 图形验证码 缓存管理类
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class VerifyCodeCacheManager {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 校验图形验证码
     */
    public boolean imgVerifyCodeOk(String sessionId, String verifyCode) {
        return Objects.equals(stringRedisTemplate.opsForValue()
            .get(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId), verifyCode);
    }

    /**
     * 从 Redis 中删除验证码
     */
    public void removeImgVerifyCode(String sessionId) {
        stringRedisTemplate.delete(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId);
    }

}
