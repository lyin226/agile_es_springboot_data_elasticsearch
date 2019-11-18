package com.agile.es.controller;

import com.agile.es.entity.Item;
import com.agile.es.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author liuyi
 * @date 2019/11/17
 */

@RestController
@RequestMapping(value = "item")
public class ItemController {


    @Autowired
    private ItemService itemService;

    /**
     * 创建索引
     * @return
     */
    @GetMapping("createIndex")
    public Map<String, Object> createIndex() {
        return itemService.createIndex();
    }


    /**
     * 插入数据
     * @param item
     * @return
     */
    @PostMapping("insert")
    public String insert(Item item) {
        return itemService.insert(item);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @GetMapping("delete")
    public String delete(long id) {
        return itemService.delete(id);
    }

    /**
     * 更新数据
     * @param id
     * @param title
     * @param category
     * @param brand
     * @param price
     * @param images
     * @return
     */
    @PostMapping("update")
    public String update(Long id, String title, String category, String brand, Double price, String images) {
        return itemService.update(id, title, category, brand, price, images);
    }

    /**
     * 自定义标题查询
     * @param title
     * @return
     */
    @GetMapping("matchQuery")
    public Page<Item> matchQuery(String title) {
        return itemService.matchQuery(title);
    }

    /**
     * 自定义价格查询
     * @param price
     * @return
     */
    @GetMapping("termQuery")
    public Page<Item> termQuery(String price) {
        return itemService.termQuery(price);
    }


    /**
     * 自定义模糊查询
     * @param title
     * @return
     */
    @GetMapping("fuzzyQuery")
    public Page<Item> fuzzyQuery(String title) {
        return itemService.fuzzyQuery(title);
    }

    /**
     * 分页查询
     * @param category
     * @return
     */
    @GetMapping("searchByPage")
    public Page<Item> searchByPage(String category) {
        return itemService.searchByPage(category);
    }

    /**
     * 排序查询
     * @param category
     * @return
     */
    @GetMapping("searchAndSort")
    public Page<Item> searchAndSort(String category) {
        return itemService.searchAndSort(category);
    }

}
