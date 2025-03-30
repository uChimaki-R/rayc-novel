package com.gitee.rayc.novel.core.api.dto.resp.book;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 小说章节相关 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
public class BookChapterAboutRespDto {

    private BookChapterRespDto chapterInfo;

    /**
     * 章节总数
     */
    private Long chapterTotal;

    /**
     * 内容概要（30字）
     */
    private String contentSummary;

}
