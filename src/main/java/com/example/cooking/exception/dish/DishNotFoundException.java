package com.example.cooking.exception.dish;

import com.example.cooking.exception.NotFoundException;

public class DishNotFoundException extends NotFoundException {
    public DishNotFoundException(Integer id) {
        super("Dish with id = " + id + " not found");
    }
}
