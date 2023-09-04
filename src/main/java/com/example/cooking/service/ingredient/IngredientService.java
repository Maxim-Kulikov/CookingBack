package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.dto.ingredient.resp.IngredientResp;
import com.example.cooking.exception.ingredient.IngredientIsExistedException;
import com.example.cooking.exception.ingredient.IngredientNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeNotFoundException;

public interface IngredientService {
    IngredientResp save(CreateIngredientReq req) throws IngredientTypeNotFoundException, IngredientIsExistedException;

    IngredientResp findById(int id) throws IngredientNotFoundException;
}