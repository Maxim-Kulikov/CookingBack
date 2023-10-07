package com.example.cooking.exception.dish;

import com.example.cooking.exception.NotFoundException;

public class DishTypeNotFoundException extends NotFoundException {
    public DishTypeNotFoundException(Integer id) {
        super("Dish type with id = " + id + " not found");
    }
}
