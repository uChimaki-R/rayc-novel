package com.gitee.rayc.novel.core.api.dto.resp.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 小说章节 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookChapterRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节ID
     */
    private Long id;

    /**
     * 小说ID
     */
    private Long bookId;

    /**
     * 章节号
     */
    private Integer chapterNum;

    /**
     * 章节名
     */
    private String chapterName;

    /**
     * 章节字数
     */
    private Integer chapterWordCount;

    /**
     * 章节更新时间
     * <p>
     * ⚠️这里的 @JsonFormat 注解必须在该类有无参构造函数的情况下才能生效
     *
     * @Builder 注解会自动帮该类生成全参构造函数，导致丢失了默认的无参构造函数。
     * <p>
     * 所以，必须显示为该类生成无参构造函数。如果没有显示为该类生成无参构造函数，会报 Json 反序列化的错误
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime chapterUpdateTime;

    /**
     * 是否收费;1-收费 0-免费
     */
    private Integer isVip;

}
