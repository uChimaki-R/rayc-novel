package com.gitee.rayc.novel.book.config;

import com.gitee.rayc.novel.book.properties.RocketProperties;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-04-04
 * @Description: RocketMQ 配置类
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties(RocketProperties.class)
public class RocketConfig {
    @Resource
    private RocketProperties rocketProperties;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQProducer producer() {
        DefaultMQProducer producer = new DefaultMQProducer(rocketProperties.getProducerGroup());
        producer.setNamesrvAddr(rocketProperties.getNamesrvAddr());
        return producer;
    }
}
