package com.example.elasticsearch.Product.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Document(indexName = "products")
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int price;
    private String name;
    private String description;

    @Builder
    public Product(int price, String name, String description) {
        this.id = null;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    protected Product() {

    }
}
