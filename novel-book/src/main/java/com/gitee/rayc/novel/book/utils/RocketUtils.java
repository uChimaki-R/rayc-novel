package com.gitee.rayc.novel.book.utils;

import com.alibaba.fastjson.JSON;
import com.gitee.rayc.novel.book.entity.BookInfo;
import com.gitee.rayc.novel.book.properties.RocketProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-04-04
 * @Description: RocketMQ 工具类
 * @Version: 1.0
 */
@Slf4j
@Component
public class RocketUtils {
    @Resource
    private RocketProperties rocketProperties;

    @Resource
    private DefaultMQProducer producer;

    public void sendBookChangeMsg(BookInfo bookInfo) {
        log.info("发送小说信息改变消息 {}", bookInfo);
        Message message = new Message(rocketProperties.getTopic(), rocketProperties.getTags(), JSON.toJSONString(bookInfo).getBytes(StandardCharsets.UTF_8));
        try {
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("收到成功回复 result: [{}] ", sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("收到失败回复 message: {}", message, throwable);
                }
            });
        } catch (Exception e) {
            log.error("发送消息 [{}] 失败", message, e);
            throw new RuntimeException(e);
        }
    }
}
