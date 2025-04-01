package com.gitee.rayc.novel.user.service.impl.rpc;

import com.gitee.rayc.novel.core.api.dto.resp.user.UserInfoRespDto;
import com.gitee.rayc.novel.core.api.service.user.UserRpcService;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户模块远程调用接口实现类
 * @Version: 1.0
 */
@DubboService
@RequiredArgsConstructor
public class UserRpcServiceImpl implements UserRpcService {

    private final UserService userService;

    /**
     * 批量查询用户信息
     */
    @Override
    public RestResp<List<UserInfoRespDto>> listUserInfoByIds(@RequestBody List<Long> userIds) {
        return userService.listUserInfoByIds(userIds);
    }

}
