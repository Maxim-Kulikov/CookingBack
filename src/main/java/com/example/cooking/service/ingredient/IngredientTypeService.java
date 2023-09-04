package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeIsExistedException;

public interface IngredientTypeService {
    IngredientTypeResp save(CreateIngredientTypeReq req) throws IngredientKindNotFoundException, IngredientTypeIsExistedException;
}
