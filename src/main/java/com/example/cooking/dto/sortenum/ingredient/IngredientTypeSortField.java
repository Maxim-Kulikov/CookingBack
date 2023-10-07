package com.example.cooking.dto.sortenum.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IngredientTypeSortField {
    PRODUCT_KIND("productKind"),
    INGREDIENT_KIND("ingredientKind"),
    INGREDIENT_TYPE("ingredientType");

    private final String value;
}
