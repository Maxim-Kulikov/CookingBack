package com.example.cooking.buisness.service.dish;

import com.example.cooking.buisness.service.MainService;
import com.example.cooking.presentation.dto.dish.req.CreateDishTypeReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishTypeReq;
import com.example.cooking.presentation.dto.dish.resp.DishNameResp;
import com.example.cooking.presentation.dto.dish.resp.DishTypeResp;

import java.util.List;

public interface DishTypeService extends MainService<CreateDishTypeReq, UpdateDishTypeReq, DishTypeResp> {
    List<DishNameResp> findAllDishNamesById(Integer id);
}
