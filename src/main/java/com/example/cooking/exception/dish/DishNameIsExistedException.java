package com.example.cooking.exception.dish;

import com.example.cooking.exception.IsExistedException;

public class DishNameIsExistedException extends IsExistedException {
    public DishNameIsExistedException(int id, String dishName) {
        super(id, "Dish name " + dishName + " is existed, it's id = " + id);
    }
}
