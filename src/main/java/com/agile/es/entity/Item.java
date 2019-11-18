package com.agile.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author liuyi
 * @date 2019/11/17
 */
@Data
@Document(indexName = "item", type = "docs", shards = 3, replicas = 1)
public class Item {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword, fielddata = true)
    private String brand;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(index = false, type = FieldType.Keyword)
    private String images;

    public Item() {

    }

    public Item(Long id, String title, String category,
                String brand, Double price, String images) {
        this.id       = id;
        this.title    = title;
        this.category = category;
        this.brand    = brand;
        this.price    = price;
        this.images   = images;
    }

}
