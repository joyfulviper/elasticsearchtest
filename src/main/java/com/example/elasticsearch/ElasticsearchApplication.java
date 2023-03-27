package com.example.elasticsearch;

import com.example.elasticsearch.Product.domain.search.ProductSearchRepository;
import com.example.elasticsearch.user.domain.search.UserSearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(excludeFilters={
        @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,
        value = UserSearchRepository.class),
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
    value = ProductSearchRepository.class)})
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

}
