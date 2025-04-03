package com.gitee.rayc.novel.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: resource模块 启动类
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.gitee.rayc.novel"})
public class NovelResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelResourceApplication.class, args);
    }

}
