package com.example.cooking.exception.ingredient;

import com.example.cooking.exception.NotFoundException;

public class IngredientTypeNotFoundException extends NotFoundException {
    public IngredientTypeNotFoundException(Integer id) {
        super("Ingredient type with id = " + id + " not found");
    }
}
