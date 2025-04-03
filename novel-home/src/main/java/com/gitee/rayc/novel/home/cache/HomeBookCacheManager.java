package com.gitee.rayc.novel.home.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookInfoRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.home.HomeBookRespDto;
import com.gitee.rayc.novel.core.api.service.book.BookRpcService;
import com.gitee.rayc.novel.core.data.constant.CacheConsts;
import com.gitee.rayc.novel.core.data.constant.DatabaseConsts;
import com.gitee.rayc.novel.home.entity.HomeBook;
import com.gitee.rayc.novel.home.mapper.HomeBookMapper;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 首页推荐小说 缓存管理类
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class HomeBookCacheManager {

    private final HomeBookMapper homeBookMapper;

    @DubboReference
    private final BookRpcService bookRpcService;

    /**
     * 查询首页小说推荐，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.HOME_BOOK_CACHE_NAME)
    public List<HomeBookRespDto> listHomeBooks() {
        // 从首页小说推荐表中查询出需要推荐的小说
        QueryWrapper<HomeBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(DatabaseConsts.CommonColumnEnum.SORT.getName());
        List<HomeBook> homeBooks = homeBookMapper.selectList(queryWrapper);

        // 获取推荐小说ID列表
        if (!CollectionUtils.isEmpty(homeBooks)) {
            List<Long> bookIds = homeBooks.stream()
                    .map(HomeBook::getBookId)
                    .collect(Collectors.toList());

            // 根据小说ID列表查询相关的小说信息列表
            List<BookInfoRespDto> bookInfos = bookRpcService.listBookInfoByIds(bookIds).getData();

            // 组装 HomeBookRespDto 列表数据并返回
            if (!CollectionUtils.isEmpty(bookInfos)) {
                Map<Long, BookInfoRespDto> bookInfoMap = bookInfos.stream()
                        .collect(Collectors.toMap(BookInfoRespDto::getId, Function.identity()));
                return homeBooks.stream().map(v -> {
                    BookInfoRespDto bookInfo = bookInfoMap.get(v.getBookId());
                    HomeBookRespDto bookRespDto = new HomeBookRespDto();
                    bookRespDto.setType(v.getType());
                    bookRespDto.setBookId(v.getBookId());
                    bookRespDto.setBookName(bookInfo.getBookName());
                    bookRespDto.setPicUrl(bookInfo.getPicUrl());
                    bookRespDto.setAuthorName(bookInfo.getAuthorName());
                    bookRespDto.setBookDesc(bookInfo.getBookDesc());
                    return bookRespDto;
                }).collect(Collectors.toList());

            }

        }

        return Collections.emptyList();
    }

    @CacheEvict(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
            value = CacheConsts.HOME_BOOK_CACHE_NAME)
    public void evictCache() {

    }

}
