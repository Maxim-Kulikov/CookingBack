package com.example.cooking.dto.sortenum.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IngredientKindSortField {
    PRODUCT_KIND("productKind"),
    INGREDIENT_KIND("ingredientKind");

    private final String value;
}
