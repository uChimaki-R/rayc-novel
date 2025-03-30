package com.gitee.rayc.novel.core.api.dto.resp.news;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 新闻信息 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
public class NewsInfoRespDto {

    /**
     * ID
     */
    private Long id;

    /**
     * 类别ID
     */
    private Long categoryId;

    /**
     * 类别名
     */
    private String categoryName;

    /**
     * 新闻来源
     */
    private String sourceName;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;

    /**
     * 新闻内容
     * */
    private String content;


}
