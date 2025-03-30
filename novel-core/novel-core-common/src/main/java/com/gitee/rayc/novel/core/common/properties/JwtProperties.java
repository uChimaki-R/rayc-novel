package com.gitee.rayc.novel.core.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-28
 * @Description: JWT 配置
 * @Version: 1.0
 */
@Data
@ConfigurationProperties("novel.jwt")
public class JwtProperties {

    // 下面的属性需要在工具类中使用, 所以改为静态属性, 需要使用set方法初始化

    /**
     * JWT 加密密钥
     */
    public static String SECRET;

    public void setSecret(String secret) {
        JwtProperties.SECRET = secret;
    }

    /**
     * 定义系统标识头常量
     */
    public static String SYSTEM_KEY_HEADER;

    public void setSystemKeyHeader(String systemKeyHeader) {
        JwtProperties.SYSTEM_KEY_HEADER = systemKeyHeader;
    }

}
