package com.gitee.rayc.novel.news.controller;

import com.gitee.rayc.novel.core.api.dto.resp.news.NewsInfoRespDto;
import com.gitee.rayc.novel.core.domain.constant.ApiRouterConsts;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 新闻模块 controller
 * @Version: 1.0
 */
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_NEWS_URL_PREFIX)
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    /**
     * 最新新闻列表查询接口
     */
    @GetMapping("latest_list")
    public RestResp<List<NewsInfoRespDto>> listLatestNews() {
        return newsService.listLatestNews();
    }

    /**
     * 新闻信息查询接口
     */
    @GetMapping("{id}")
    public RestResp<NewsInfoRespDto> getNews(@PathVariable Long id) {
        return newsService.getNews(id);
    }
}
