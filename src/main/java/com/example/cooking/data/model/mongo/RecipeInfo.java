package com.example.cooking.data.model.mongo;

import com.example.cooking.data.model.postgres.dish.Dish;
import com.example.cooking.presentation.dto.ingredient.UsedIngredient;
import com.example.cooking.presentation.dto.photo.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
