package com.example.cooking.service.dish;

import com.example.cooking.dto.dish.req.CreateDishTypeReq;
import com.example.cooking.dto.dish.req.UpdateDishTypeReq;
import com.example.cooking.dto.dish.resp.DishNameResp;
import com.example.cooking.dto.dish.resp.DishTypeResp;
import com.example.cooking.exception.dish.DishTypeNotFoundException;
import com.example.cooking.service.MainService;

import java.util.List;

public interface DishTypeService extends MainService<CreateDishTypeReq, UpdateDishTypeReq, DishTypeResp> {
    List<DishNameResp> findAllDishNamesById(Integer id);
}
