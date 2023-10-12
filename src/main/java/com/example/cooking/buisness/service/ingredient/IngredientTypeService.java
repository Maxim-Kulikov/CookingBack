package com.example.cooking.buisness.service.ingredient;

import com.example.cooking.buisness.service.MainService;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateIngredientTypeReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientResp;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientTypeResp;

import java.util.List;

public interface IngredientTypeService extends MainService<CreateIngredientTypeReq, UpdateIngredientTypeReq, IngredientTypeResp> {
    List<IngredientResp> findAllIngredientsById(Integer id);
}
