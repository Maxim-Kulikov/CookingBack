package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.service.MainService;

import java.util.List;

public interface ProductKindService extends MainService<CreateProductKindReq, UpdateProductKindReq, ProductKindResp> {
    List<IngredientKindResp> findAllIngredientKinds(Integer id);
}
