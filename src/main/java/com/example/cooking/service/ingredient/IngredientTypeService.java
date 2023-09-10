package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientTypeReq;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeIsExistedException;
import com.example.cooking.exception.ingredient.IngredientTypeNotFoundException;

public interface IngredientTypeService {
    IngredientTypeResp save(CreateIngredientTypeReq req) throws IngredientKindNotFoundException, IngredientTypeIsExistedException;

    IngredientTypeResp update(Integer id, UpdateIngredientTypeReq req) throws IngredientTypeNotFoundException, IngredientTypeIsExistedException, IngredientKindNotFoundException;

    void delete(Integer id) throws IngredientTypeNotFoundException;

    IngredientTypeResp findById(Integer id) throws IngredientTypeNotFoundException;
}
