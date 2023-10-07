package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientTypeReq;
import com.example.cooking.dto.ingredient.resp.IngredientResp;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.service.MainService;

import java.util.List;

public interface IngredientTypeService extends MainService<CreateIngredientTypeReq, UpdateIngredientTypeReq, IngredientTypeResp> {
    List<IngredientResp> findAllIngredientsById(Integer id);
}
