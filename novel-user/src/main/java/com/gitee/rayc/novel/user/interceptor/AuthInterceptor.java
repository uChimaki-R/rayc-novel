package com.gitee.rayc.novel.user.interceptor;

import com.gitee.rayc.novel.core.api.dto.entity.user.UserInfoDto;
import com.gitee.rayc.novel.core.common.context.AuthContext;
import com.gitee.rayc.novel.core.common.utils.JwtUtils;
import com.gitee.rayc.novel.core.domain.constant.SystemConfigConsts;
import com.gitee.rayc.novel.core.domain.enums.RespCodeEnum;
import com.gitee.rayc.novel.core.domain.exception.BusinessException;
import com.gitee.rayc.novel.user.cache.UserInfoCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户信息解析拦截器
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final UserInfoCacheManager userInfoCacheManager;

    /**
     * handle 执行前调用
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 获取登录 JWT
        String token = request.getHeader(SystemConfigConsts.HTTP_AUTH_HEADER_NAME);

        // 开始认证
        if (!StringUtils.hasText(token)) {
            // token 为空
            throw new BusinessException(RespCodeEnum.USER_LOGIN_EXPIRED);
        }
        Long userId = JwtUtils.parseToken(token, SystemConfigConsts.NOVEL_FRONT_KEY);
        if (Objects.isNull(userId)) {
            // token 解析失败
            throw new BusinessException(RespCodeEnum.USER_LOGIN_EXPIRED);
        }
        UserInfoDto userInfo = userInfoCacheManager.getUser(userId);
        if (Objects.isNull(userInfo)) {
            // 用户不存在
            throw new BusinessException(RespCodeEnum.USER_ACCOUNT_NOT_EXIST);
        }
        // 设置 userId 到当前线程
        AuthContext.setUserId(userId);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * handler 执行后调用，出现异常不调用
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * DispatcherServlet 完全处理完请求后调用，出现异常照常调用
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        // 清理当前线程保存的用户数据
        AuthContext.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
