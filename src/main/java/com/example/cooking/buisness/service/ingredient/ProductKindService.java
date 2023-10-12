package com.example.cooking.buisness.service.ingredient;

import com.example.cooking.presentation.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.presentation.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.buisness.service.MainService;

import java.util.List;

public interface ProductKindService extends MainService<CreateProductKindReq, UpdateProductKindReq, ProductKindResp> {
    List<IngredientKindResp> findAllIngredientKinds(Integer id);
}
