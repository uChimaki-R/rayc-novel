package com.gitee.rayc.novel.book.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gitee.rayc.novel.book.entity.BookCategory;
import com.gitee.rayc.novel.book.mapper.BookCategoryMapper;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookCategoryRespDto;
import com.gitee.rayc.novel.core.data.constant.CacheConsts;
import com.gitee.rayc.novel.core.data.constant.DatabaseConsts;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 小说分类 缓存管理类
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class BookCategoryCacheManager {

    private final BookCategoryMapper bookCategoryMapper;

    /**
     * 根据作品方向查询小说分类列表，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
        value = CacheConsts.BOOK_CATEGORY_LIST_CACHE_NAME)
    public List<BookCategoryRespDto> listCategory(Integer workDirection) {
        QueryWrapper<BookCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookCategoryTable.COLUMN_WORK_DIRECTION, workDirection);
        return bookCategoryMapper.selectList(queryWrapper).stream().map(v ->
            BookCategoryRespDto.builder()
                .id(v.getId())
                .name(v.getName())
                .build()).collect(Collectors.toList());
    }

}
