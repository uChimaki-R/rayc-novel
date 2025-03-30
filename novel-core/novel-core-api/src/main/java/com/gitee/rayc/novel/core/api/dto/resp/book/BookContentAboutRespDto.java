package com.gitee.rayc.novel.core.api.dto.resp.book;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 小说内容相关 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
public class BookContentAboutRespDto {

    /**
     * 小说信息
     */
    private BookInfoRespDto bookInfo;

    /**
     * 章节信息
     */
    private BookChapterRespDto chapterInfo;

    /**
     * 章节内容
     */
    private String bookContent;

}
