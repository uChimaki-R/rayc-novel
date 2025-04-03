package com.gitee.rayc.novel.home.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gitee.rayc.novel.core.api.dto.resp.home.HomeFriendLinkRespDto;
import com.gitee.rayc.novel.core.data.constant.CacheConsts;
import com.gitee.rayc.novel.core.data.constant.DatabaseConsts;
import com.gitee.rayc.novel.home.entity.HomeFriendLink;
import com.gitee.rayc.novel.home.mapper.HomeFriendLinkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 友情链接 缓存管理类
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class FriendLinkCacheManager {

    private final HomeFriendLinkMapper friendLinkMapper;

    /**
     * 友情链接列表查询，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
        value = CacheConsts.HOME_FRIEND_LINK_CACHE_NAME)
    public List<HomeFriendLinkRespDto> listFriendLinks() {
        // 从友情链接表中查询出友情链接列表
        QueryWrapper<HomeFriendLink> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(DatabaseConsts.CommonColumnEnum.SORT.getName());
        return friendLinkMapper.selectList(queryWrapper).stream().map(v -> {
            HomeFriendLinkRespDto respDto = new HomeFriendLinkRespDto();
            respDto.setLinkName(v.getLinkName());
            respDto.setLinkUrl(v.getLinkUrl());
            return respDto;
        }).collect(Collectors.toList());
    }

}
