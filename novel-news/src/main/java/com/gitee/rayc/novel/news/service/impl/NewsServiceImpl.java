package com.gitee.rayc.novel.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gitee.rayc.novel.core.api.dto.resp.news.NewsInfoRespDto;
import com.gitee.rayc.novel.core.data.constant.DatabaseConsts;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.news.cache.NewsCacheManager;
import com.gitee.rayc.novel.news.entity.NewsContent;
import com.gitee.rayc.novel.news.entity.NewsInfo;
import com.gitee.rayc.novel.news.mapper.NewsContentMapper;
import com.gitee.rayc.novel.news.mapper.NewsInfoMapper;
import com.gitee.rayc.novel.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 新闻模块 服务实现类
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsCacheManager newsCacheManager;

    private final NewsInfoMapper newsInfoMapper;

    private final NewsContentMapper newsContentMapper;

    @Override
    public RestResp<List<NewsInfoRespDto>> listLatestNews() {
        return RestResp.ok(newsCacheManager.listLatestNews());
    }

    @Override
    public RestResp<NewsInfoRespDto> getNews(Long id) {
        NewsInfo newsInfo = newsInfoMapper.selectById(id);
        QueryWrapper<NewsContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.NewsContentTable.COLUMN_NEWS_ID, id)
            .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        NewsContent newsContent = newsContentMapper.selectOne(queryWrapper);
        return RestResp.ok(NewsInfoRespDto.builder()
            .title(newsInfo.getTitle())
            .sourceName(newsInfo.getSourceName())
            .updateTime(newsInfo.getUpdateTime())
            .content(newsContent.getContent())
            .build());
    }
}
