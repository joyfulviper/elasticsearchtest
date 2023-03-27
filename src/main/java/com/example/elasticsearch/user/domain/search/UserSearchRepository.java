package com.example.elasticsearch.user.domain.search;

import com.example.elasticsearch.user.domain.User;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserSearchRepository extends ElasticsearchRepository<User, Long>, CustomUserSearchRepository {
    List<User> findByBasicProfile_NameContains(String name);
    List<User> findAllByBasicProfile_DescriptionContains(String description, Pageable pageable);
}
