package com.gitee.rayc.novel.core.api.dto.resp.book;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 小说分类 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
public class BookCategoryRespDto {

    /**
     * 类别ID
     */
    private Long id;

    /**
     * 类别名
     */
    private String name;

}
