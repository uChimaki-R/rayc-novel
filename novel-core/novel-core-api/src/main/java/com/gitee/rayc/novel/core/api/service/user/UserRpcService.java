package com.gitee.rayc.novel.core.api.service.user;

import com.gitee.rayc.novel.core.api.dto.resp.user.UserInfoRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户远程调用service
 * @Version: 1.0
 */
public interface UserRpcService {
    RestResp<List<UserInfoRespDto>> listUserInfoByIds(List<Long> userIds);
}
