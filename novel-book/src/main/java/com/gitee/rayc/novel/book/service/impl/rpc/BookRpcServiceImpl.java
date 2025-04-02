package com.gitee.rayc.novel.book.service.impl.rpc;

import com.gitee.rayc.novel.book.service.BookService;
import com.gitee.rayc.novel.core.api.dto.req.book.*;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookChapterRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookEsRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookInfoRespDto;
import com.gitee.rayc.novel.core.api.service.book.BookRpcService;
import com.gitee.rayc.novel.core.domain.resp.PageRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 小说微服务内部调用接口
 * @Version: 1.0
 */
@DubboService
@RequiredArgsConstructor
public class BookRpcServiceImpl implements BookRpcService {

    private final BookService bookService;

    /**
     * 查询下一批保存到 ES 中的小说列表
     */
    @Override
    public RestResp<List<BookEsRespDto>> listNextEsBooks(@RequestBody Long maxBookId) {
        return bookService.listNextEsBooks(maxBookId);
    }

    /**
     * 批量查询小说信息
     */
    @Override
    public RestResp<List<BookInfoRespDto>> listBookInfoByIds(@RequestBody List<Long> bookIds) {
        return bookService.listBookInfoByIds(bookIds);
    }

    /**
     * 发表评论接口
     */
    @Override
    public RestResp<Void> publishComment(@Valid @RequestBody BookCommentReqDto dto) {
        return bookService.saveComment(dto);
    }

    /**
     * 修改评论接口
     */
    @Override
    public RestResp<Void> updateComment(@Valid @RequestBody BookCommentReqDto dto) {
        return bookService.updateComment(dto);
    }

    /**
     * 删除评论接口
     */
    @Override
    public RestResp<Void> deleteComment(@RequestBody BookCommentReqDto dto) {
        return bookService.deleteComment(dto);
    }

    /**
     * 小说发布接口
     */
    @Override
    public RestResp<Void> publishBook(@Valid @RequestBody BookAddReqDto dto) {
        return bookService.saveBook(dto);
    }

    /**
     * 小说发布列表查询接口
     */
    @Override
    public RestResp<PageRespDto<BookInfoRespDto>> listPublishBooks(@RequestBody BookPageReqDto dto) {
        return bookService.listAuthorBooks(dto);
    }

    /**
     * 小说章节发布接口
     */
    @Override
    public RestResp<Void> publishBookChapter(@Valid @RequestBody ChapterAddReqDto dto) {
        return bookService.saveBookChapter(dto);
    }

    /**
     * 小说章节发布列表查询接口
     */
    @Override
    public RestResp<PageRespDto<BookChapterRespDto>> listPublishBookChapters(@RequestBody ChapterPageReqDto dto) {
        return bookService.listBookChapters(dto);
    }

}
