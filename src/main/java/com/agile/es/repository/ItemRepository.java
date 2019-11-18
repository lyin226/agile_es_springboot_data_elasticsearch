package com.agile.es.repository;

import com.agile.es.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author liuyi
 * @date 2019/11/17
 */

@Component
public interface ItemRepository extends ElasticsearchRepository<Item, Long>{

}
