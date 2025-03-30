package com.gitee.rayc.novel.core.api.dto.req.book;

import javax.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 章节发布 请求DTO
 * @Version: 1.0
 */
@Data
public class ChapterAddReqDto {

    /**
     * 小说ID
     */
    private Long bookId;

    /**
     * 作家ID
     */
    private Long authorId;

    /**
     * 章节名
     */
    @NotBlank
    private String chapterName;

    /**
     * 章节内容
     */
    @NotBlank
    @Length(min = 50)
    private String chapterContent;

    /**
     * 是否收费;1-收费 0-免费
     */
    @NotNull
    private Integer isVip;

}
