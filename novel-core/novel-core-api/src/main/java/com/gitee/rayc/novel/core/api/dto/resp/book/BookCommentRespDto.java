package com.gitee.rayc.novel.core.api.dto.resp.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gitee.rayc.novel.core.common.codec.UsernameSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 小说评论 响应DTO
 * @Version: 1.0
 */
@Data
@Builder
public class BookCommentRespDto {

    private Long commentTotal;

    private List<CommentInfo> comments;

    @Data
    @Builder
    public static class CommentInfo {

        private Long id;

        private String commentContent;

        @JsonSerialize(using = UsernameSerializer.class)
        private String commentUser;

        private Long commentUserId;

        private String commentUserPhoto;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime commentTime;

    }

}
