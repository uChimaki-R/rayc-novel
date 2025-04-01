package com.gitee.rayc.novel.user.service;

import com.gitee.rayc.novel.core.api.dto.req.user.UserInfoUptReqDto;
import com.gitee.rayc.novel.core.api.dto.req.user.UserLoginReqDto;
import com.gitee.rayc.novel.core.api.dto.req.user.UserRegisterReqDto;
import com.gitee.rayc.novel.core.api.dto.resp.user.UserInfoRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.user.UserLoginRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.user.UserRegisterRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户模块 服务接口
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param dto 注册参数
     * @return JWT
     */
    RestResp<UserRegisterRespDto> register(UserRegisterReqDto dto);

    /**
     * 用户登录
     *
     * @param dto 登录参数
     * @return JWT + 昵称
     */
    RestResp<UserLoginRespDto> login(UserLoginReqDto dto);

    /**
     * 用户反馈
     *
     * @param userId  反馈用户ID
     * @param content 反馈内容
     * @return void
     */
    RestResp<Void> saveFeedback(Long userId, String content);

    /**
     * 用户信息修改
     *
     * @param dto 用户信息
     * @return void
     */
    RestResp<Void> updateUserInfo(UserInfoUptReqDto dto);

    /**
     * 用户反馈删除
     *
     * @param userId 用户ID
     * @param id     反馈ID
     * @return void
     */
    RestResp<Void> deleteFeedback(Long userId, Long id);

    /**
     * 查询书架状态接口
     *
     * @param userId 用户ID
     * @param bookId 小说ID
     * @return 0-不在书架 1-已在书架
     */
    RestResp<Integer> getBookshelfStatus(Long userId, String bookId);

    /**
     * 用户信息查询
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    RestResp<UserInfoRespDto> getUserInfo(Long userId);

    /**
     * 批量查询用户信息
     *
     * @param userIds 用户ID列表
     * @return 用户信息列表
     */
    RestResp<List<UserInfoRespDto>> listUserInfoByIds(List<Long> userIds);
}
