package com.gitee.rayc.novel.resource.config;

import com.gitee.rayc.novel.core.domain.constant.SystemConfigConsts;
import com.gitee.rayc.novel.resource.interceptor.FileInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: Spring Web Mvc 相关配置不要加 @EnableWebMvc 注解，否则会导致 jackson 的全局配置失效。因为 @EnableWebMvc 注解会导致 WebMvcAutoConfiguration 自动配置失效
 * @Version: 1.0
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final FileInterceptor fileInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 文件访问拦截
        registry.addInterceptor(fileInterceptor)
            .addPathPatterns(SystemConfigConsts.IMAGE_UPLOAD_DIRECTORY + "**")
            .order(1);

    }

}
