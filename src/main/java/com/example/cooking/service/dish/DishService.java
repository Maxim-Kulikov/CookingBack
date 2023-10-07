package com.example.cooking.service.dish;

import com.example.cooking.dto.dish.req.CreateDishReq;
import com.example.cooking.dto.dish.req.UpdateDishReq;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.service.MainService;

public interface DishService extends MainService<CreateDishReq, UpdateDishReq, DishResp> {
}
