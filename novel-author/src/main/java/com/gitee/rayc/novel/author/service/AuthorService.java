package com.gitee.rayc.novel.author.service;

import com.gitee.rayc.novel.core.api.dto.req.author.AuthorRegisterReqDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 作家模块 服务类
 * @Version: 1.0
 */
public interface AuthorService {

    /**
     * 作家注册
     *
     * @param dto 注册参数
     * @return void
     */
    RestResp<Void> register(AuthorRegisterReqDto dto);

    /**
     * 查询作家状态
     *
     * @param userId 用户ID
     * @return 作家状态
     */
    RestResp<Integer> getStatus(Long userId);
}
