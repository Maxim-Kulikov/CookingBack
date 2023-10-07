package com.example.cooking.service.dish;

import com.example.cooking.dto.dish.req.CreateDishNameReq;
import com.example.cooking.dto.dish.req.UpdateDishNameReq;
import com.example.cooking.dto.dish.resp.DishNameResp;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.service.MainService;

import java.util.List;

public interface DishNameService extends MainService<CreateDishNameReq, UpdateDishNameReq, DishNameResp> {
    List<DishResp> findAllDishesById(Integer id);
}
