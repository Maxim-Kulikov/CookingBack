package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.exception.ingredient.IngredientKindIsExistedException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;

public interface IngredientKindService {
    IngredientKindResp save(CreateIngredientKindReq req) throws ProductKindNotFoundException, IngredientKindIsExistedException;
}
