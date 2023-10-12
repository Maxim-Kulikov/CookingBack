package com.example.cooking.buisness.service.ingredient;

import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.presentation.dto.ingredient.req.IngredientFilterReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateIngredientReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientResp;
import com.example.cooking.buisness.service.MainService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IngredientService extends MainService<CreateIngredientReq, UpdateIngredientReq, IngredientResp> {

    @Transactional
    List<IngredientResp> findAllWithFilter(IngredientFilterReq filter);
}
