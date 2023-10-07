package com.example.cooking.service.dish;

import com.example.cooking.dto.dish.req.CreateMealTimeReq;
import com.example.cooking.dto.dish.req.UpdateMealTimeReq;
import com.example.cooking.dto.dish.resp.DishTypeResp;
import com.example.cooking.dto.dish.resp.MealTimeResp;
import com.example.cooking.exception.dish.MealTimeNotFoundException;
import com.example.cooking.service.MainService;

import java.util.List;

public interface MealTimeService extends MainService<CreateMealTimeReq, UpdateMealTimeReq, MealTimeResp> {
    List<DishTypeResp> findAllDishTypesById(Integer id);
}
