package com.gitee.rayc.novel.author.controller;

import com.gitee.rayc.novel.author.service.AuthorService;
import com.gitee.rayc.novel.core.api.dto.req.author.AuthorRegisterReqDto;
import com.gitee.rayc.novel.core.api.dto.req.book.BookAddReqDto;
import com.gitee.rayc.novel.core.api.dto.req.book.BookPageReqDto;
import com.gitee.rayc.novel.core.api.dto.req.book.ChapterAddReqDto;
import com.gitee.rayc.novel.core.api.dto.req.book.ChapterPageReqDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookChapterRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookInfoRespDto;
import com.gitee.rayc.novel.core.api.service.book.BookRpcService;
import com.gitee.rayc.novel.core.common.context.AuthContext;
import com.gitee.rayc.novel.core.domain.constant.ApiRouterConsts;
import com.gitee.rayc.novel.core.domain.req.PageReqDto;
import com.gitee.rayc.novel.core.domain.resp.PageRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 
 * @Version: 1.0
 */
@RestController
@RequestMapping(ApiRouterConsts.API_AUTHOR_URL_PREFIX)
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @DubboReference
    private final BookRpcService bookRpcService;

    /**
     * 作家注册接口
     */
    @PostMapping("register")
    public RestResp<Void> register(@Valid @RequestBody AuthorRegisterReqDto dto) {
        dto.setUserId(AuthContext.getUserId());
        return authorService.register(dto);
    }

    /**
     * 查询作家状态接口
     */
    @GetMapping("status")
    public RestResp<Integer> getStatus() {
        return authorService.getStatus(AuthContext.getUserId());
    }

    /**
     * 小说发布接口
     */
    @PostMapping("book")
    public RestResp<Void> publishBook(@Valid @RequestBody BookAddReqDto dto) {
        return bookRpcService.publishBook(dto);
    }

    /**
     * 小说发布列表查询接口
     */
    @GetMapping("books")
    public RestResp<PageRespDto<BookInfoRespDto>> listBooks(BookPageReqDto dto) {
        dto.setAuthorId(AuthContext.getAuthorId());
        return bookRpcService.listPublishBooks(dto);
    }

    /**
     * 小说章节发布接口
     */
    @PostMapping("book/chapter/{bookId}")
    public RestResp<Void> publishBookChapter(@PathVariable("bookId") Long bookId,
                                             @Valid @RequestBody ChapterAddReqDto dto) {
        dto.setAuthorId(AuthContext.getAuthorId());
        dto.setBookId(bookId);
        return bookRpcService.publishBookChapter(dto);
    }

    /**
     * 小说章节发布列表查询接口
     */
    @GetMapping("book/chapters/{bookId}")
    public RestResp<PageRespDto<BookChapterRespDto>> listBookChapters(@PathVariable("bookId") Long bookId, PageReqDto dto) {
        ChapterPageReqDto chapterPageReqReqDto = new ChapterPageReqDto();
        chapterPageReqReqDto.setBookId(bookId);
        chapterPageReqReqDto.setPageNum(dto.getPageNum());
        chapterPageReqReqDto.setPageSize(dto.getPageSize());
        return bookRpcService.listPublishBookChapters(chapterPageReqReqDto);
    }

}
