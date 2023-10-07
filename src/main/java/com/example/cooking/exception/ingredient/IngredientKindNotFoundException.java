package com.example.cooking.exception.ingredient;

import com.example.cooking.exception.NotFoundException;

public class IngredientKindNotFoundException extends NotFoundException {
    public IngredientKindNotFoundException(Integer id) {
        super("Ingredient kind with id = " + id + " not found");
    }
}
