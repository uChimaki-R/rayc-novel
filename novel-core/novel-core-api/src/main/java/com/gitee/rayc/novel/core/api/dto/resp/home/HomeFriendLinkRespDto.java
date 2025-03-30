package com.gitee.rayc.novel.core.api.dto.resp.home;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 首页友情链接 响应DTO
 * @Version: 1.0
 */
@Data
public class HomeFriendLinkRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 链接名
     */
    private String linkName;

    /**
     * 链接url
     */
    private String linkUrl;
}
