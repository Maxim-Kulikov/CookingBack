package com.example.cooking.buisness.service.dish;

import com.example.cooking.buisness.service.MainService;
import com.example.cooking.presentation.dto.dish.req.CreateDishReq;
import com.example.cooking.presentation.dto.dish.req.DishFilterReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishReq;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import com.example.cooking.data.model.postgres.dish.Dish;

import java.util.List;

public interface DishService extends MainService<CreateDishReq, UpdateDishReq, DishResp> {
    List<Dish> getDishesByUserId(int userId);

    List<DishResp> findAllByFilter(DishFilterReq req);
}
