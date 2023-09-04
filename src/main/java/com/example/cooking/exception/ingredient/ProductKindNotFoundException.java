package com.example.cooking.exception.ingredient;

public class ProductKindNotFoundException extends NotFoundException {
    public ProductKindNotFoundException(Integer id) {
        super("Product kind with id = " + id + " not found");
    }
}
