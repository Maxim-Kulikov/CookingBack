package com.example.cooking.service.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.service.MainService;

import java.util.List;

public interface IngredientKindService extends MainService<CreateIngredientKindReq, UpdateIngredientKindReq, IngredientKindResp> {
    List<IngredientTypeResp> findAllIngredientTypesById(Integer id);
}
