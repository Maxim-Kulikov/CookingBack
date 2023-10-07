package com.example.cooking.dto.sortenum.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IngredientSortField {
    PRODUCT_KIND("productKind"),
    INGREDIENT_KIND("ingredientKind"),
    INGREDIENT_TYPE("ingredientType"),
    INGREDIENT_NAME("ingredientName"),
    CALORIES("calories"),
    PROTEINS("proteins"),
    FATS("fats"),
    CARBOHYDRATES("carbohydrates");

    private final String value;
}
