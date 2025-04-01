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
 * @Description: 用户消费记录
 * @Version: 1.0
 */
@Data
@TableName("user_consume_log")
public class UserConsumeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消费用户ID
     */
    private Long userId;

    /**
     * 消费使用的金额;单位：屋币
     */
    private Integer amount;

    /**
     * 消费商品类型;0-小说VIP章节
     */
    private Integer productType;

    /**
     * 消费的的商品ID;例如：章节ID
     */
    private Long productId;

    /**
     * 消费的的商品名;例如：章节名
     */
    private String producName;

    /**
     * 消费的的商品值;例如：1
     */
    private Integer producValue;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
