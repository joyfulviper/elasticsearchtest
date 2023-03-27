package com.example.elasticsearch.Product.domain.search;

import com.example.elasticsearch.Product.domain.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<Product, Long> {
    List<Product> findAllByName(String name);
}
