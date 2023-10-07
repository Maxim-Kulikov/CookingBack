package com.example.cooking.exception.ingredient;

import com.example.cooking.exception.IsExistedException;

public class IngredientTypeIsExistedException extends IsExistedException {
    public IngredientTypeIsExistedException(int id, String type) {
        super(id, "Ingredient type " + type + " is existed, it's id = " + id);
    }
}
