package com.gitee.rayc.novel.search.task;

import org.apache.dubbo.config.annotation.DubboReference;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookEsRespDto;
import com.gitee.rayc.novel.core.api.service.book.BookRpcService;
import com.gitee.rayc.novel.core.data.constant.EsConsts;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 小说数据同步到 elasticsearch 任务
 * @Version: 1.0
 */
@Component
@Slf4j
public class BookToEsTask {
    @DubboReference
    private BookRpcService bookRpcService;

    @Resource
    private RestHighLevelClient client;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    @XxlJob("saveToEsJobHandler")
    public ReturnT<String> saveToEs() {
        try {
            long maxId = 0;
            for (; ; ) {
                List<BookEsRespDto> books = bookRpcService.listNextEsBooks(maxId).getData();
                if (books.isEmpty()) {
                    break;
                }

                BulkRequest bulkRequest = new BulkRequest();

                for (BookEsRespDto book : books) {
                    IndexRequest indexRequest = new IndexRequest(EsConsts.BookIndex.INDEX_NAME)
                            .id(book.getId().toString())
                            .source(convertToMap(book), XContentType.JSON);
                    bulkRequest.add(indexRequest);
                    maxId = book.getId();
                }

                BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);

                if (response.hasFailures()) {
                    log.error("批量操作存在失败记录");
                    for (BulkItemResponse item : response.getItems()) {
                        if (item.isFailed()) {
                            log.error("文档ID {} 同步失败: {}", item.getId(),
                                    item.getFailure().getMessage());
                        }
                    }
                }
            }
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            log.error("ES同步任务异常: {}", e.getMessage(), e);
            return ReturnT.FAIL;
        }
    }

    private Map<String, Object> convertToMap(BookEsRespDto book) {
        return OBJECT_MAPPER.convertValue(
                book,
                new TypeReference<Map<String, Object>>() {
                }
        );
    }
}