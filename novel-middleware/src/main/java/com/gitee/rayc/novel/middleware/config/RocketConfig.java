package com.gitee.rayc.novel.middleware.config;

import com.alibaba.fastjson.JSON;
import com.gitee.rayc.novel.core.api.dto.entity.book.BookInfoDto;
import com.gitee.rayc.novel.core.api.dto.entity.es.EsBookDto;
import com.gitee.rayc.novel.core.data.constant.EsConsts;
import com.gitee.rayc.novel.middleware.properties.RocketProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-04-04
 * @Description: RocketMQ 配置类
 * @Version: 1.0
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(RocketProperties.class)
public class RocketConfig {
    @Resource
    private RocketProperties rocketProperties;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * RocketMQ消费者
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQPushConsumer consumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketProperties.getConsumerGroup());
        consumer.setNamesrvAddr(rocketProperties.getNamesrvAddr());
        try {
            consumer.subscribe(rocketProperties.getTopic(), "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    list.forEach(message -> {
                        log.info("收到更新小说文档消息 {}", new String(message.getBody(), StandardCharsets.UTF_8));
                        BookInfoDto bookInfoDto = JSON.parseObject(new String(message.getBody(), StandardCharsets.UTF_8), BookInfoDto.class);
                        // 构造es文档
                        EsBookDto esBookDto = EsBookDto.build(bookInfoDto);
                        // 构造请求
                        IndexRequest indexRequest = new IndexRequest()
                                .index(EsConsts.BookIndex.INDEX_NAME)
                                .id(bookInfoDto.getId().toString())
                                .source(JSON.toJSONString(esBookDto), XContentType.JSON);
                        try {
                            restHighLevelClient.index(
                                    indexRequest,
                                    RequestOptions.DEFAULT
                            );
                            log.info("更新小说文档成功 {}", esBookDto);
                        } catch (IOException e) {
                            log.error("更新小说文档失败 {}", esBookDto, e);
                            throw new RuntimeException(e);
                        }
                    });
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            return consumer;
        } catch (Exception e) {
            log.error("创建consumer失败", e);
            throw new RuntimeException(e);
        }
    }
}
