package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.exception.ingredient.ProductKindIsExistedException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.model.postgres.ingredient.IngredientKind;

import java.util.List;

public interface ProductKindService {
    ProductKindResp save(CreateProductKindReq req) throws ProductKindIsExistedException;

    ProductKindResp update(Integer id, UpdateProductKindReq req) throws ProductKindIsExistedException, ProductKindNotFoundException;

    void delete(Integer id) throws ProductKindNotFoundException;

    ProductKindResp findById(Integer id) throws ProductKindNotFoundException;

    List<IngredientKindResp> findAllIngredientKinds(Integer id) throws ProductKindNotFoundException;

    List<ProductKindResp> findAll();
}
