package com.example.cooking.exception.ingredient;

public class IngredientNotFoundException extends NotFoundException {
    public IngredientNotFoundException(Integer id) {
        super("Ingredient with id = " + id + " not found");
    }
}
