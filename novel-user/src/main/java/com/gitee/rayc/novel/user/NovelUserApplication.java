package com.gitee.rayc.novel.user;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: 用户模块 启动类
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.gitee.rayc.novel"})
@MapperScan("com.gitee.rayc.novel.user.mapper")
@EnableCaching
@EnableDubbo
public class NovelUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelUserApplication.class, args);
    }

}
