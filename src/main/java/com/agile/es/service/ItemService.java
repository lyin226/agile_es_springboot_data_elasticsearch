package com.agile.es.service;

import com.agile.es.entity.Item;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author liuyi
 * @date 2019/11/17
 */
public interface ItemService {

    /**
     * 创建索引
     * @return
     */
    Map<String, Object> createIndex();


    /**
     * 新建数据
     * @param item
     * @return
     */
    String insert(Item item);

    /**
     * 删除
     * @param id
     * @return
     */
    String delete(long id);

    /**
     * 更新
     * @param id
     * @param title
     * @param category
     * @param brand
     * @param price
     * @param images
     * @return
     */
    String update(Long id, String title, String category, String brand, Double price, String images);

    /**
     * 自定义查询
     * @param title
     * @return
     */
    Page<Item> matchQuery(String title);

    /**
     *
     * @param price  精确查询
     * @return
     */
    Page<Item> termQuery(String price);

    /**
     * 自定义模糊查询
     * @param title
     * @return
     */
    Page<Item> fuzzyQuery(String title);

    /**
     * 分页查询
     * @param category
     * @return
     */
    Page<Item> searchByPage(String category);

    /**
     * 分页排序查询
     * @param category
     * @return
     */
    Page<Item> searchAndSort(String category);

}
