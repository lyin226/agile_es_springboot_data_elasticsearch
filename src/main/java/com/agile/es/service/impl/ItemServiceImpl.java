package com.agile.es.service.impl;

import com.agile.es.entity.Item;
import com.agile.es.repository.ItemRepository;
import com.agile.es.service.ItemService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyi
 * @date 2019/11/17
 */

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SUCCESS = "success";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public Map<String, Object> createIndex() {
        HashMap<String, Object> map = new HashMap<>();
        boolean result = elasticsearchTemplate.createIndex(Item.class);
        map.put("result", result);
        return map;
    }

    @Override
    public String insert(Item item) {
        itemRepository.save(item);
        return SUCCESS;
    }

    @Override
    public String delete(long id){
        itemRepository.deleteById(id);
        return SUCCESS;
    }

    @Override
    public String update(Long id, String title, String category, String brand, Double price, String images){
        Item item = new Item(id, title, category, brand, price, images);
        itemRepository.save(item);
        return SUCCESS;
    }

    @Override
    public Page<Item> matchQuery(String title) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", title));
        Page<Item> page = this.itemRepository.search(queryBuilder.build());
        logger.info("ItemServiceImpl total:{}", page.getTotalElements());
        return page;
    }

    @Override
    public Page<Item> termQuery(String price) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.termQuery("price", price));
        Page<Item> page = this.itemRepository.search(builder.build());
        return page;
    }


    @Override
    public Page<Item> fuzzyQuery(String title) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.fuzzyQuery("title", title));
        Page<Item> page = this.itemRepository.search(builder.build());
        return page;
    }

    @Override
    public Page<Item> searchByPage(String category) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("category", category));
        int pageNum = 0;
        int size    = 2;
        queryBuilder.withPageable(PageRequest.of(pageNum, size));
        Page<Item> page = this.itemRepository.search(queryBuilder.build());
        logger.info("ItemServiceImpl searchByPage total:{}, totalPage:{}, number:{}, size:{}",
                page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getSize());
        return page;
    }

    @Override
    public Page<Item> searchAndSort(String category) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("category", category));
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        Page<Item> page = this.itemRepository.search(queryBuilder.build());
        logger.info("ItemServiceImpl searchByPage total:{}, totalPage:{}, number:{}, size:{}",
                page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getSize());
        return page;
    }


}
