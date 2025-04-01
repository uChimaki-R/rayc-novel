package com.gitee.rayc.novel.user.controller;

import com.gitee.rayc.novel.core.api.dto.req.book.BookCommentReqDto;
import com.gitee.rayc.novel.core.api.dto.req.user.UserInfoUptReqDto;
import com.gitee.rayc.novel.core.api.dto.req.user.UserLoginReqDto;
import com.gitee.rayc.novel.core.api.dto.req.user.UserRegisterReqDto;
import com.gitee.rayc.novel.core.api.dto.resp.user.UserInfoRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.user.UserLoginRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.user.UserRegisterRespDto;
import com.gitee.rayc.novel.core.api.service.book.BookRpcService;
import com.gitee.rayc.novel.core.common.context.AuthContext;
import com.gitee.rayc.novel.core.domain.constant.ApiRouterConsts;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户模块 controller
 * @Version: 1.0
 */
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_USER_URL_PREFIX)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DubboReference
    private final BookRpcService bookRpcService;

    /**
     * 用户注册接口
     */
    @PostMapping("register")
    public RestResp<UserRegisterRespDto> register(@Valid @RequestBody UserRegisterReqDto dto) {
        return userService.register(dto);
    }

    /**
     * 用户登录接口
     */
    @PostMapping("login")
    public RestResp<UserLoginRespDto> login(@Valid @RequestBody UserLoginReqDto dto) {
        return userService.login(dto);
    }

    /**
     * 用户信息查询接口
     */
    @GetMapping
    public RestResp<UserInfoRespDto> getUserInfo() {
        return userService.getUserInfo(AuthContext.getUserId());
    }

    /**
     * 用户信息修改接口
     */
    @PutMapping
    public RestResp<Void> updateUserInfo(@Valid @RequestBody UserInfoUptReqDto dto) {
        dto.setUserId(AuthContext.getUserId());
        return userService.updateUserInfo(dto);
    }

    /**
     * 用户反馈提交接口
     */
    @PostMapping("feedback")
    public RestResp<Void> submitFeedback(@RequestBody String content) {
        return userService.saveFeedback(AuthContext.getUserId(), content);
    }

    /**
     * 用户反馈删除接口
     */
    @DeleteMapping("feedback/{id}")
    public RestResp<Void> deleteFeedback(@PathVariable Long id) {
        return userService.deleteFeedback(AuthContext.getUserId(), id);
    }

    /**
     * 发表评论接口
     */
    @PostMapping("comment")
    public RestResp<Void> comment(@Valid @RequestBody BookCommentReqDto dto) {
        return bookRpcService.publishComment(dto);
    }

    /**
     * 修改评论接口
     */
    @PutMapping("comment/{id}")
    public RestResp<Void> updateComment(@PathVariable Long id,
                                        String content) {
        BookCommentReqDto dto = new BookCommentReqDto();
        dto.setUserId(AuthContext.getUserId());
        dto.setCommentId(id);
        dto.setCommentContent(content);
        return bookRpcService.updateComment(dto);
    }

    /**
     * 删除评论接口
     */
    @DeleteMapping("comment/{id}")
    public RestResp<Void> deleteComment(@PathVariable Long id) {
        BookCommentReqDto dto = new BookCommentReqDto();
        dto.setUserId(AuthContext.getUserId());
        dto.setCommentId(id);
        return bookRpcService.deleteComment(dto);
    }

    /**
     * 查询书架状态接口 0-不在书架 1-已在书架
     */
    @GetMapping("bookshelf_status")
    public RestResp<Integer> getBookshelfStatus(String bookId) {
        return userService.getBookshelfStatus(AuthContext.getUserId(), bookId);
    }

}
