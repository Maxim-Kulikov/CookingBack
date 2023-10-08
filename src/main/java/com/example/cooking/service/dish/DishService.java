package com.example.cooking.service.dish;

import com.example.cooking.dto.dish.req.CreateDishReq;
import com.example.cooking.dto.dish.req.DishFilterReq;
import com.example.cooking.dto.dish.req.UpdateDishReq;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.service.MainService;

import java.util.List;

public interface DishService extends MainService<CreateDishReq, UpdateDishReq, DishResp> {
    List<DishResp> findAllByFilter(DishFilterReq req);
}
