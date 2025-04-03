package com.gitee.rayc.novel.home.service;

import com.gitee.rayc.novel.core.api.dto.resp.home.HomeBookRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.home.HomeFriendLinkRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 首页模块 服务类
 * @Version: 1.0
 */
public interface HomeService {

    /**
     * 查询首页小说推荐列表
     *
     * @return 首页小说推荐列表的 rest 响应结果
     */
    RestResp<List<HomeBookRespDto>> listHomeBooks();

    /**
     * 首页友情链接列表查询
     *
     * @return 友情链接列表
     */
    RestResp<List<HomeFriendLinkRespDto>> listHomeFriendLinks();
}
