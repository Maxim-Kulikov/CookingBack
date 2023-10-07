package com.example.cooking.exception.dish;

import com.example.cooking.exception.NotFoundException;

public class MealTimeNotFoundException extends NotFoundException {
    public MealTimeNotFoundException(Integer id) {
        super("Meal time with id = " + id + " not found");
    }
}
