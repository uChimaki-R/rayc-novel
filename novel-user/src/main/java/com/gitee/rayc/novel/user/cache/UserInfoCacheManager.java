package com.gitee.rayc.novel.user.cache;

import com.gitee.rayc.novel.core.api.dto.entity.user.UserInfoDto;
import com.gitee.rayc.novel.core.data.constant.CacheConsts;
import com.gitee.rayc.novel.user.entity.UserInfo;
import com.gitee.rayc.novel.user.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户信息 缓存管理类
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class UserInfoCacheManager {

    private final UserInfoMapper userInfoMapper;

    /**
     * 查询用户信息，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.USER_INFO_CACHE_NAME)
    public UserInfoDto getUser(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if (Objects.isNull(userInfo)) {
            return null;
        }
        return UserInfoDto.builder()
                .id(userInfo.getId())
                .status(userInfo.getStatus()).build();
    }


}
