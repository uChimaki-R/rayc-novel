package com.gitee.rayc.novel.resource.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 文件 拦截器
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class FileInterceptor implements HandlerInterceptor {

    @Value("${novel.file.upload.path}")
    private String fileUploadPath;

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 获取请求的 URI
        String requestUri = request.getRequestURI();
        // 缓存10天
        response.setDateHeader("expires", System.currentTimeMillis() + 60 * 60 * 24 * 10 * 1000);
        try (OutputStream out = response.getOutputStream(); InputStream input = Files.newInputStream(Paths.get(fileUploadPath + requestUri))) {
            byte[] b = new byte[4096];
            for (int n; (n = input.read(b)) != -1; ) {
                out.write(b, 0, n);
            }
        }
        return false;
    }

}
