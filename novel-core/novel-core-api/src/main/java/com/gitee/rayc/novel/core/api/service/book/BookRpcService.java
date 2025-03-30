package com.gitee.rayc.novel.core.api.service.book;

import com.gitee.rayc.novel.core.api.dto.req.book.*;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookChapterRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookEsRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookInfoRespDto;
import com.gitee.rayc.novel.core.domain.resp.PageRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 小说远程调用service
 * @Version: 1.0
 */
public interface BookRpcService {

    /**
     * 查询下一批保存到 ES 中的小说列表
     */
    RestResp<List<BookEsRespDto>> listNextEsBooks(Long maxBookId);

    /**
     * 批量查询小说信息
     */
    RestResp<List<BookInfoRespDto>> listBookInfoByIds(List<Long> bookIds);

    /**
     * 发表评论
     */
    RestResp<Void> publishComment(BookCommentReqDto dto);

    /**
     * 修改评论
     */
    RestResp<Void> updateComment(BookCommentReqDto dto);

    /**
     * 删除评论接口
     */
    RestResp<Void> deleteComment(@RequestBody BookCommentReqDto dto);

    /**
     * 小说发布接口
     */
    RestResp<Void> publishBook(BookAddReqDto dto);

    /**
     * 小说发布列表查询接口
     */
    RestResp<PageRespDto<BookInfoRespDto>> listPublishBooks(BookPageReqDto dto);

    /**
     * 小说章节发布接口
     */
    RestResp<Void> publishBookChapter(ChapterAddReqDto dto);

    /**
     * 小说章节发布列表查询接口
     */
    RestResp<PageRespDto<BookChapterRespDto>> listPublishBookChapters(ChapterPageReqDto dto);
}
