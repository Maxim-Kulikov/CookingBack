package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.exception.ingredient.ProductKindIsExistedException;

public interface ProductKindService {
    ProductKindResp save(CreateProductKindReq req) throws ProductKindIsExistedException;
}
