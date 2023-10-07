package com.example.cooking.dto.dish.resp;

import com.example.cooking.dto.ingredient.resp.UsedIngredientResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishResp {
    private Integer id;
    private Integer cookingTime;
    private Double calories;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;

    private Integer idUser;

    private String idRecipeInfo;
    private Integer idDishName;
    private String dishName;

    private Integer idDishType;
    private String dishType;

    private Integer idMealTime;
    private String dayPart;
}
