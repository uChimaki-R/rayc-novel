package com.gitee.rayc.novel.book.cache;

import com.gitee.rayc.novel.book.entity.BookChapter;
import com.gitee.rayc.novel.book.mapper.BookChapterMapper;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookChapterRespDto;
import com.gitee.rayc.novel.core.data.constant.CacheConsts;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 小说章节 缓存管理类
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class BookChapterCacheManager {

    private final BookChapterMapper bookChapterMapper;

    /**
     * 查询小说章节信息，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
        value = CacheConsts.BOOK_CHAPTER_CACHE_NAME)
    public BookChapterRespDto getChapter(Long chapterId) {
        BookChapter bookChapter = bookChapterMapper.selectById(chapterId);
        return BookChapterRespDto.builder()
            .id(chapterId)
            .bookId(bookChapter.getBookId())
            .chapterNum(bookChapter.getChapterNum())
            .chapterName(bookChapter.getChapterName())
            .chapterWordCount(bookChapter.getWordCount())
            .chapterUpdateTime(bookChapter.getUpdateTime())
            .build();
    }


}
