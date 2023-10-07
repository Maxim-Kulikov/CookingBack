package com.example.cooking.controller.dish;

import com.example.cooking.dto.dish.req.CreateDishTypeReq;
import com.example.cooking.dto.dish.resp.DishTypeResp;
import com.example.cooking.service.dish.DishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishes/types")
public class DishTypeController {
    @Autowired
    private DishTypeService dishTypeService;

    @PostMapping("/save")
    public DishTypeResp save(@RequestBody CreateDishTypeReq dto) {
        return dishTypeService.save(dto);
    }
}
