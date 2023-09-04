package com.example.cooking.exception.ingredient;

import lombok.Getter;

@Getter
public class IsExistedException extends Exception{
    private int id;

    public  IsExistedException(int id, String message) {
        super(message);
        this.id = id;
    }
}
