package com.gitee.rayc.novel.home.service.impl;

import com.gitee.rayc.novel.core.api.dto.resp.home.HomeBookRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.home.HomeFriendLinkRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.home.cache.FriendLinkCacheManager;
import com.gitee.rayc.novel.home.cache.HomeBookCacheManager;
import com.gitee.rayc.novel.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 首页模块 服务实现类
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeBookCacheManager homeBookCacheManager;

    private final FriendLinkCacheManager friendLinkCacheManager;

    @Override
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        List<HomeBookRespDto> list = homeBookCacheManager.listHomeBooks();
        if(CollectionUtils.isEmpty(list)){
            homeBookCacheManager.evictCache();
        }
        return RestResp.ok(list);
    }

    @Override
    public RestResp<List<HomeFriendLinkRespDto>> listHomeFriendLinks() {
        return RestResp.ok(friendLinkCacheManager.listFriendLinks());
    }
}
