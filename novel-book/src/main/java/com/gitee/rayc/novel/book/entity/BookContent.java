package com.gitee.rayc.novel.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 小说内容
 * @Version: 1.0
 */
@Data
@TableName("book_content")
public class BookContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 章节ID
     */
    private Long chapterId;

    /**
     * 小说章节内容
     */
    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
