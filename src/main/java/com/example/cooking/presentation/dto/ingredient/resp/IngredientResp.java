package com.example.cooking.presentation.dto.ingredient.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientResp {
    private Integer id;
    private Integer calories;
    private Integer proteins;
    private Integer fats;
    private Integer carbohydrates;

    private Integer idIngredientType;
    private String ingredientType;

    private Integer idIngredientKind;
    private String ingredientKind;

    private Integer idProductKind;
    private String productKind;

    private String ingredientName;
}
