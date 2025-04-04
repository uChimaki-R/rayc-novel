package com.gitee.rayc.novel.search.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import com.gitee.rayc.novel.core.api.dto.req.book.BookSearchReqDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookEsRespDto;
import com.gitee.rayc.novel.core.api.dto.resp.book.BookInfoRespDto;
import com.gitee.rayc.novel.core.data.constant.EsConsts;
import com.gitee.rayc.novel.core.domain.resp.PageRespDto;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: Elasticsearch 搜索 服务实现类
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {

    private final RestHighLevelClient client;

    @SneakyThrows
    @Override
    public RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition) {
        SearchRequest searchRequest = new SearchRequest(EsConsts.BookIndex.INDEX_NAME);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 构建检索条件
        buildSearchCondition(condition, sourceBuilder);

        // 排序
        if (StringUtils.isNotBlank(condition.getSort())) {
            String sortField = StringUtils.underlineToCamel(condition.getSort().split(" ")[0]);
            sourceBuilder.sort(new FieldSortBuilder(sortField).order(SortOrder.DESC));
        }

        // 分页
        sourceBuilder.from((condition.getPageNum() - 1) * condition.getPageSize());
        sourceBuilder.size(condition.getPageSize());

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder()
            .field(new HighlightBuilder.Field(EsConsts.BookIndex.FIELD_BOOK_NAME)
                .preTags("<em style='color:red'>").postTags("</em>"))
            .field(new HighlightBuilder.Field(EsConsts.BookIndex.FIELD_AUTHOR_NAME)
                .preTags("<em style='color:red'>").postTags("</em>"));
        sourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        // 处理结果
        long totalHits = Objects.requireNonNull(response.getHits().getTotalHits()).value;
        List<BookInfoRespDto> list = new ArrayList<>();

        for (SearchHit hit : response.getHits().getHits()) {
            BookEsRespDto book = (BookEsRespDto) hit.getSourceAsMap();

            // 处理高亮
            if (hit.getHighlightFields().containsKey(EsConsts.BookIndex.FIELD_BOOK_NAME)) {
                book.setBookName(hit.getHighlightFields().get(EsConsts.BookIndex.FIELD_BOOK_NAME).fragments()[0].toString());
            }
            if (hit.getHighlightFields().containsKey(EsConsts.BookIndex.FIELD_AUTHOR_NAME)) {
                book.setAuthorName(hit.getHighlightFields().get(EsConsts.BookIndex.FIELD_AUTHOR_NAME).fragments()[0].toString());
            }

            list.add(BookInfoRespDto.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .categoryId(book.getCategoryId())
                .categoryName(book.getCategoryName())
                .authorId(book.getAuthorId())
                .authorName(book.getAuthorName())
                .wordCount(book.getWordCount())
                .lastChapterName(book.getLastChapterName())
                .build());
        }

        return RestResp.ok(
            PageRespDto.of(condition.getPageNum(), condition.getPageSize(), totalHits, list));
    }

    private void buildSearchCondition(BookSearchReqDto condition, SearchSourceBuilder sourceBuilder) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // 必须包含字数 > 0
        boolQuery.must(QueryBuilders.rangeQuery(EsConsts.BookIndex.FIELD_WORD_COUNT).gt(0));

        // 关键词搜索
        if (StringUtils.isNotBlank(condition.getKeyword())) {
            MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(condition.getKeyword())
                .field(EsConsts.BookIndex.FIELD_BOOK_NAME, 2.0f)
                .field(EsConsts.BookIndex.FIELD_AUTHOR_NAME, 1.8f)
                .field(EsConsts.BookIndex.FIELD_BOOK_DESC, 0.1f)
                .fuzziness(Fuzziness.AUTO);
            boolQuery.must(multiMatchQuery);
        }

        // 精确查询
        if (Objects.nonNull(condition.getWorkDirection())) {
            boolQuery.must(new TermQueryBuilder(
                EsConsts.BookIndex.FIELD_WORK_DIRECTION,
                condition.getWorkDirection()));
        }

        if (Objects.nonNull(condition.getCategoryId())) {
            boolQuery.must(new TermQueryBuilder(
                EsConsts.BookIndex.FIELD_CATEGORY_ID,
                condition.getCategoryId()));
        }

        // 范围查询
        if (Objects.nonNull(condition.getWordCountMin())) {
            boolQuery.must(QueryBuilders.rangeQuery(EsConsts.BookIndex.FIELD_WORD_COUNT)
                .gte(condition.getWordCountMin()));
        }

        if (Objects.nonNull(condition.getWordCountMax())) {
            boolQuery.must(QueryBuilders.rangeQuery(EsConsts.BookIndex.FIELD_WORD_COUNT)
                .lt(condition.getWordCountMax()));
        }

        if (Objects.nonNull(condition.getUpdateTimeMin())) {
            boolQuery.must(QueryBuilders.rangeQuery(EsConsts.BookIndex.FIELD_LAST_CHAPTER_UPDATE_TIME)
                .gte(condition.getUpdateTimeMin().getTime()));
        }

        sourceBuilder.query(boolQuery);
    }
}