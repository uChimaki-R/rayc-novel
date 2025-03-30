package com.gitee.rayc.novel.core.common.interceptor;

import com.gitee.rayc.novel.core.common.utils.JwtUtils;
import com.gitee.rayc.novel.core.common.context.AuthContext;
import com.gitee.rayc.novel.core.domain.constant.SystemConfigConsts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: Token 解析拦截器
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class TokenParseInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 获取登录 JWT
        String token = request.getHeader(SystemConfigConsts.HTTP_AUTH_HEADER_NAME);
        if (StringUtils.hasText(token)) {
            // 解析 token 并保存
            AuthContext.setUserId(JwtUtils.parseToken(token, SystemConfigConsts.NOVEL_FRONT_KEY));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        // 清理当前线程保存的用户数据
        AuthContext.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
