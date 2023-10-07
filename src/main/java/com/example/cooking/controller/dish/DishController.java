package com.example.cooking.controller.dish;

import com.example.cooking.dto.dish.req.CreateDishReq;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.service.dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping("/save")
    public DishResp save(@RequestBody CreateDishReq dto) {
        return dishService.save(dto);
    }
}
