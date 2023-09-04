package com.example.cooking.exception.ingredient;

public class ProductKindIsExistedException extends IsExistedException {
    public ProductKindIsExistedException(int id, String kind) {
        super(id, "Product kind " + kind + " is existed, it's id = " + id);
    }
}
