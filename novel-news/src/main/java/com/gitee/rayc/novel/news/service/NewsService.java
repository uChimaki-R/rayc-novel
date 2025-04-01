package com.gitee.rayc.novel.news.service;

import com.gitee.rayc.novel.core.api.dto.resp.news.NewsInfoRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 新闻模块 服务类
 * @Version: 1.0
 */
public interface NewsService {

    /**
     * 最新新闻列表查询
     *
     * @return 新闻列表
     */
    RestResp<List<NewsInfoRespDto>> listLatestNews();

    /**
     * 新闻信息查询
     *
     * @param id 新闻ID
     * @return 新闻信息
     */
    RestResp<NewsInfoRespDto> getNews(Long id);
}
