package com.example.cooking.repository.mongo;

import com.example.cooking.model.mongo.RecipeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;
public interface RecipeInfoRepository extends MongoRepository<RecipeInfo, String> {
    Optional<RecipeInfo> findFirstById(String id);
}
