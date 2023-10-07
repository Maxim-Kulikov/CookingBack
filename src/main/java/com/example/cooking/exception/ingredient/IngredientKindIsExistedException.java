package com.example.cooking.exception.ingredient;

import com.example.cooking.exception.IsExistedException;

public class IngredientKindIsExistedException extends IsExistedException {
    public IngredientKindIsExistedException(int id, String kind) {
        super(id, "Ingredient kind " + kind + " is existed, it's id = " + id);
    }
}
