package com.gitee.rayc.novel.core.api.dto.req.book;

import com.gitee.rayc.novel.core.domain.req.PageReqDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 章节发布页 请求DTO
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookPageReqDto extends PageReqDto {

    /**
     * 作家ID
     */
    private Long authorId;


}
