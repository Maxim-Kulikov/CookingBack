package com.example.cooking.buisness.service.ingredient;

import com.example.cooking.buisness.service.MainService;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateIngredientKindReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientTypeResp;

import java.util.List;

public interface IngredientKindService extends MainService<CreateIngredientKindReq, UpdateIngredientKindReq, IngredientKindResp> {
    List<IngredientTypeResp> findAllIngredientTypesById(Integer id);
}
