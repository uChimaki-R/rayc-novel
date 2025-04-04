package com.gitee.rayc.novel.search.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-31
 * @Description: es 配置属性
 * @Version: 1.0
 */
@Data
@ConfigurationProperties("novel.es")
public class EsProperties {
    private String host;
    private Integer port;
}
