package com.example.cooking.exception.dish;

import com.example.cooking.exception.IsExistedException;

public class MealTimeIsExistedException extends IsExistedException {
    public MealTimeIsExistedException(int id, String dayPart) {
        super(id, "Meal time " + dayPart + " is existed, it's id = " + id);
    }
}
