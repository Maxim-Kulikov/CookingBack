package com.example.cooking.presentation.dto.dish.resp;

import com.example.cooking.presentation.dto.ingredient.UsedIngredient;
import com.example.cooking.presentation.dto.photo.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishResp {
    private Integer id;
    private String name;
    private Integer cookingTime;
    private Double calories;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;

    private Integer idUser;

    private Integer idDishName;
    private String dishName;

    private Integer idDishType;
    private String dishType;

    private Integer idMealTime;
    private String dayPart;

    private List<UsedIngredient> usedIngredients;
    private Photo photo;
}
