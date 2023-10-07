package com.example.cooking.exception.dish;

import com.example.cooking.exception.NotFoundException;

public class RecipeInfoNotFoundException extends NotFoundException {
    public RecipeInfoNotFoundException(String id) {
        super("Recipe info with id = " + id + " not found");
    }

    public RecipeInfoNotFoundException(int idRecipe) {
        super("Recipe info with id of recipe = " + idRecipe + " not found");
    }
}
