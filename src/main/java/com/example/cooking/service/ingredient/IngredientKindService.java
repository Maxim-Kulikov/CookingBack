package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.exception.ingredient.IngredientKindIsExistedException;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;

import java.util.List;

public interface IngredientKindService {
    IngredientKindResp save(CreateIngredientKindReq req) throws ProductKindNotFoundException, IngredientKindIsExistedException;

    IngredientKindResp update(Integer id, UpdateIngredientKindReq req) throws ProductKindNotFoundException, IngredientKindIsExistedException, IngredientKindNotFoundException;

    void delete(Integer id) throws IngredientKindNotFoundException;

    IngredientKindResp findById(Integer id) throws IngredientKindNotFoundException;
}
