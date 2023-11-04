package com.example.cooking.data.repository.mongo;

import com.example.cooking.data.model.mongo.RecipeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface RecipeInfoRepository extends MongoRepository<RecipeInfo, String> {

}
