package com.example.cooking.exception.dish;

import com.example.cooking.exception.IsExistedException;

public class DishIsExistedException extends IsExistedException {
    public DishIsExistedException(int id, String dishType) {
        super(id, "Dish type " + dishType + " is existed, it's id = " + id);
    }
}
