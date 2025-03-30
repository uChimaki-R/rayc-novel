package com.gitee.rayc.novel.core.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-29
 * @Description: Xss 过滤配置属性
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "novel.xss")
public class XssProperties {
    private Boolean enabled;
    private List<String> excludes;
}
