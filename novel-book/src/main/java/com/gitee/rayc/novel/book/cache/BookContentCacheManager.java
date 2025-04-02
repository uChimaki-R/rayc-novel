package com.gitee.rayc.novel.book.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gitee.rayc.novel.book.entity.BookContent;
import com.gitee.rayc.novel.book.mapper.BookContentMapper;
import com.gitee.rayc.novel.core.data.constant.CacheConsts;
import com.gitee.rayc.novel.core.data.constant.DatabaseConsts;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 小说内容 缓存管理类
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class BookContentCacheManager {

    private final BookContentMapper bookContentMapper;

    /**
     * 查询小说内容，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
        value = CacheConsts.BOOK_CONTENT_CACHE_NAME)
    public String getBookContent(Long chapterId) {
        QueryWrapper<BookContent> contentQueryWrapper = new QueryWrapper<>();
        contentQueryWrapper.eq(DatabaseConsts.BookContentTable.COLUMN_CHAPTER_ID, chapterId)
            .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookContent bookContent = bookContentMapper.selectOne(contentQueryWrapper);
        return bookContent.getContent();
    }


}
