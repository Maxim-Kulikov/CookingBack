package com.example.cooking.exception.ingredient;

import com.example.cooking.exception.NotFoundException;

public class ProductKindNotFoundException extends NotFoundException {
    public ProductKindNotFoundException(Integer id) {
        super("Product kind with id = " + id + " not found");
    }
}
