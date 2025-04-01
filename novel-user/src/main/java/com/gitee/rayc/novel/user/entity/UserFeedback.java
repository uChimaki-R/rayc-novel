package com.gitee.rayc.novel.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 用户反馈
 * @Version: 1.0
 */
@Data
@TableName("user_feedback")
public class UserFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 反馈用户id
     */
    private Long userId;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
