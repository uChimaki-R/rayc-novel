package com.gitee.rayc.novel.search.controller;

import com.gitee.rayc.novel.core.api.dto.req.book.BookSearchReqDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookInfoRespDto;
import com.gitee.rayc.novel.core.domain.constant.ApiRouterConsts;
import com.gitee.rayc.novel.core.domain.resp.PageRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 前台门户-搜索模块 API 控制器
 * @Version: 1.0
 */
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_SEARCH_URL_PREFIX)
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    /**
     * 小说搜索接口
     */
    @GetMapping("books")
    public RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition) {
        return searchService.searchBooks(condition);
    }

}
