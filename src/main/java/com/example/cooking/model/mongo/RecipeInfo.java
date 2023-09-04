package com.example.cooking.model.mongo;

import com.example.cooking.dto.ingredient.resp.UsedIngredientForMongo;
import com.example.cooking.dto.photo.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "recipes_info")
public class RecipeInfo {
    @Id
    private String id;
    private Integer idRecipe;
    private List<UsedIngredientForMongo> usedIngredients;
    private Photo photo;
}
