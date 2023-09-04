package com.example.cooking.exception.ingredient;

public class IngredientTypeNotFoundException extends NotFoundException {
    public IngredientTypeNotFoundException(Integer id) {
        super("Ingredient type with id = " + id + " not found");
    }
}
