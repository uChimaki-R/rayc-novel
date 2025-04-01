package com.gitee.rayc.novel.news;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 新闻模块 启动类
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.gitee.rayc.novel"})
@MapperScan("com.gitee.rayc.novel.news.mapper")
@EnableCaching
public class NovelNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelNewsApplication.class, args);
    }

}
