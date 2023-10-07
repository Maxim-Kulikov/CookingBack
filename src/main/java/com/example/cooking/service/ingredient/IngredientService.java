package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientReq;
import com.example.cooking.dto.ingredient.resp.IngredientResp;
import com.example.cooking.service.MainService;

public interface IngredientService extends MainService<CreateIngredientReq, UpdateIngredientReq, IngredientResp> {

}
