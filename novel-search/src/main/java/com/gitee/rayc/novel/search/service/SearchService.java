package com.gitee.rayc.novel.search.service;

import com.gitee.rayc.novel.core.api.dto.req.book.BookSearchReqDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookInfoRespDto;
import com.gitee.rayc.novel.core.domain.resp.PageRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 搜索 服务类
 * @Version: 1.0
 */
public interface SearchService {

    /**
     * 小说搜索
     *
     * @param condition 搜索条件
     * @return 搜索结果
     */
    RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition);

}
