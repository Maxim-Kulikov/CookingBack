package com.example.cooking.exception.ingredient;

import com.example.cooking.exception.IsExistedException;

public class IngredientIsExistedException extends IsExistedException {
    public IngredientIsExistedException(int id, String ingredientName) {
        super(id, "Ingredient with name " + ingredientName + " is existed, it's id = " + id);
    }
}
