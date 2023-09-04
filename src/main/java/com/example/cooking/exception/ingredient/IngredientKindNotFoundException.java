package com.example.cooking.exception.ingredient;

public class IngredientKindNotFoundException extends NotFoundException {
    public IngredientKindNotFoundException(Integer id) {
        super("Ingredient kind with id = " + id + " not found");
    }
}
