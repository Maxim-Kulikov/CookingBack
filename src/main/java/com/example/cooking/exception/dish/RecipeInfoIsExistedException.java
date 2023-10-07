package com.example.cooking.exception.dish;

import com.example.cooking.exception.IsExistedException;

public class RecipeInfoIsExistedException extends IsExistedException {
    public RecipeInfoIsExistedException(int id, String dishType) {
        super(id, "Dish type " + dishType + " is existed, it's id = " + id);
    }
}
