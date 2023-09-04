package com.example.cooking.repository.mongo;

import com.example.cooking.model.mongo.RecipeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeInfoDao extends MongoRepository<RecipeInfo, String> {

}
