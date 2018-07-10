package com.javaforjava.bootifulelasticsearch.dal;

import com.javaforjava.bootifulelasticsearch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
}
