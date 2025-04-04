package com.gitee.rayc.novel.search.config;

import com.gitee.rayc.novel.search.properties.EsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: Elasticsearch 相关配置
 * @Version: 1.0
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(EsProperties.class)
public class EsConfig {
    @Resource
    private EsProperties properties;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(properties.getHost(), properties.getPort())));
    }

}
