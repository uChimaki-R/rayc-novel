package com.gitee.rayc.novel.core.common.codec;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;


/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-28
 * @Description: json 全局反序列化器
 * @Version: 1.0
 */
@JsonComponent
public class GlobalJsonDeserializer {

    /**
     * 字符串反序列化器：过滤特殊字符，解决 XSS 攻击
     */
    public static class StringDeserializer extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException {
            return jsonParser.getValueAsString()
                .replace("<", "&lt;")
                .replace(">", "&gt;");
        }
    }
}
