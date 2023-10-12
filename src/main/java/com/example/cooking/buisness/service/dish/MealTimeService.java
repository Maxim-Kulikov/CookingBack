package com.example.cooking.buisness.service.dish;

import com.example.cooking.buisness.service.MainService;
import com.example.cooking.presentation.dto.dish.req.CreateMealTimeReq;
import com.example.cooking.presentation.dto.dish.req.UpdateMealTimeReq;
import com.example.cooking.presentation.dto.dish.resp.DishTypeResp;
import com.example.cooking.presentation.dto.dish.resp.MealTimeResp;

import java.util.List;

public interface MealTimeService extends MainService<CreateMealTimeReq, UpdateMealTimeReq, MealTimeResp> {
    List<DishTypeResp> findAllDishTypesById(Integer id);
}
