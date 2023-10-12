package com.example.cooking.buisness.service.dish;

import com.example.cooking.buisness.service.MainService;
import com.example.cooking.presentation.dto.dish.req.CreateDishNameReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishNameReq;
import com.example.cooking.presentation.dto.dish.resp.DishNameResp;
import com.example.cooking.presentation.dto.dish.resp.DishResp;

import java.util.List;

public interface DishNameService extends MainService<CreateDishNameReq, UpdateDishNameReq, DishNameResp> {
    List<DishResp> findAllDishesById(Integer id);
}
