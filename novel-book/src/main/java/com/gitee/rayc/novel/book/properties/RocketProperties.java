package com.gitee.rayc.novel.book.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-04-04
 * @Description: RocketMQ 配置属性类
 * @Version: 1.0
 */
@Data
@ConfigurationProperties("novel.rocket")
public class RocketProperties {
    private String namesrvAddr;
    private String producerGroup;
    private String topic;
    private String tags;
}
