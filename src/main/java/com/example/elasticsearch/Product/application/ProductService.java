package com.example.elasticsearch.Product.application;

import com.example.elasticsearch.Product.domain.Product;
import com.example.elasticsearch.Product.domain.ProductRepository;
import com.example.elasticsearch.Product.domain.search.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductSearchRepository productSearchRepository;

    @Transactional
    public void save(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .price(productRequestDto.getPrice())
                .description(productRequestDto.getDescription())
                .name(productRequestDto.getName())
                .build();
        productRepository.save(product);
        productSearchRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> searchByName(String name) {
        return productSearchRepository.findAllByName(name)
                .stream()
                .map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }
}
