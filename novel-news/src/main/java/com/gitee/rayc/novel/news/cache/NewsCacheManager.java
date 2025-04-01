package com.gitee.rayc.novel.news.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gitee.rayc.novel.core.api.dto.resp.news.NewsInfoRespDto;
import com.gitee.rayc.novel.core.data.constant.CacheConsts;
import com.gitee.rayc.novel.core.data.constant.DatabaseConsts;
import com.gitee.rayc.novel.news.entity.NewsInfo;
import com.gitee.rayc.novel.news.mapper.NewsInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 新闻 缓存管理类
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class NewsCacheManager {

    private final NewsInfoMapper newsInfoMapper;

    /**
     * 最新新闻列表查询，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
        value = CacheConsts.LATEST_NEWS_CACHE_NAME)
    public List<NewsInfoRespDto> listLatestNews() {
        // 从新闻信息表中查询出最新发布的两条新闻
        QueryWrapper<NewsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(DatabaseConsts.CommonColumnEnum.CREATE_TIME.getName())
            .last(DatabaseConsts.SqlEnum.LIMIT_2.getSql());
        return newsInfoMapper.selectList(queryWrapper).stream().map(v -> NewsInfoRespDto.builder()
            .id(v.getId())
            .categoryId(v.getCategoryId())
            .categoryName(v.getCategoryName())
            .title(v.getTitle())
            .sourceName(v.getSourceName())
            .updateTime(v.getUpdateTime())
            .build()).collect(Collectors.toList());
    }

}
