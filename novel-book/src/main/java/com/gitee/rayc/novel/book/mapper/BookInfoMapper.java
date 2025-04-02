package com.gitee.rayc.novel.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gitee.rayc.novel.book.entity.BookInfo;
import com.gitee.rayc.novel.core.api.dto.req.book.BookSearchReqDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookInfoRespDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 小说信息 Mapper 接口
 * @Version: 1.0
 */
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    /**
     * 增加小说点击量
     *
     * @param bookId 小说ID
     */
    void addVisitCount(@Param("bookId") Long bookId);

    /**
     * 小说搜索
     * @param page mybatis-plus 分页对象
     * @param condition 搜索条件
     * @return 返回结果
     * */
    List<BookInfo> searchBooks(IPage<BookInfoRespDto> page, BookSearchReqDto condition);

}
