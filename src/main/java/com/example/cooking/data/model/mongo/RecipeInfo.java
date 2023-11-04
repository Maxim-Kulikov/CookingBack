package com.example.cooking.data.model.mongo;

import com.example.cooking.presentation.dto.ingredient.UsedIngredient;
import com.example.cooking.presentation.dto.photo.Photo;
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
    private List<UsedIngredient> usedIngredients;
    private Photo photo;
}
