package com.example.elasticsearch.Product.application;

import com.example.elasticsearch.Product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private final int price;
    private final String name;
    private final String description;

    @Builder
    public ProductResponseDto(int price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .price(product.getPrice())
                .description(product.getDescription())
                .name(product.getName())
                .build();
    }
}
