package com.example.cooking.exception;

import lombok.Getter;

@Getter
public class IsExistedException extends RuntimeException {
    private int id;

    public  IsExistedException(int id, String message) {
        super(message);
        this.id = id;
    }

    public IsExistedException(String message) {
        super(message);
    }
}
