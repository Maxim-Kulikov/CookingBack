package com.example.cooking.buisness.service.dish;

import com.example.cooking.buisness.service.MainService;
import com.example.cooking.presentation.dto.dish.req.CreateDishReq;
import com.example.cooking.presentation.dto.dish.req.DishFilterReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishReq;
import com.example.cooking.presentation.dto.dish.resp.DishResp;

import java.util.List;

public interface DishService extends MainService<CreateDishReq, UpdateDishReq, DishResp> {
    List<DishResp> findAllByFilter(DishFilterReq req);
}
