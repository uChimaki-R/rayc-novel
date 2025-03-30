package com.gitee.rayc.novel.core.api.dto.req.book;

import lombok.Data;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 小说评论 请求DTO
 * @Version: 1.0
 */
@Data
public class BookCommentReqDto {

    private Long commentId;

    private Long userId;

    private Long bookId;

    private String commentContent;

}
