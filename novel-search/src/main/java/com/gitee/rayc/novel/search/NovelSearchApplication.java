package com.gitee.rayc.novel.search;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: search模块 启动类
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.gitee.rayc.novel"})
@EnableDubbo
public class NovelSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelSearchApplication.class, args);
    }

}
