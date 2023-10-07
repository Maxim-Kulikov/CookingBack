package com.example.cooking.exception.ingredient;

import com.example.cooking.exception.NotFoundException;

public class IngredientNotFoundException extends NotFoundException {
    public IngredientNotFoundException(Integer id) {
        super("Ingredient with id = " + id + " not found");
    }
}
