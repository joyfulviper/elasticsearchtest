package com.example.elasticsearch.Product.application;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    private final int price;
    private final String description;
    private final String name;

    public ProductRequestDto(int price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }
}
