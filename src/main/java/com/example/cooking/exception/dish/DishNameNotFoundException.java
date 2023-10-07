package com.example.cooking.exception.dish;

import com.example.cooking.exception.NotFoundException;

public class DishNameNotFoundException extends NotFoundException {
    public DishNameNotFoundException(Integer id) {
        super("Dish name with id = " + id + " not found");
    }
}
