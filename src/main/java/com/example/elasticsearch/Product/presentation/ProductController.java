package com.example.elasticsearch.Product.presentation;

import com.example.elasticsearch.Product.application.ProductRequestDto;
import com.example.elasticsearch.Product.application.ProductResponseDto;
import com.example.elasticsearch.Product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/product")
    public ResponseEntity<Void> save(@RequestBody ProductRequestDto productRequestDto) {
        productService.save(productRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/product/{name}")
    public ResponseEntity<List<ProductResponseDto>> searchByName(@PathVariable String name) {
        List<ProductResponseDto> productResponseDto = productService.searchByName(name);
        return ResponseEntity.ok(productResponseDto);
    }
}
