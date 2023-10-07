package com.example.cooking.controller.dish;

import com.example.cooking.dto.dish.req.CreateDishNameReq;
import com.example.cooking.dto.dish.resp.DishNameResp;
import com.example.cooking.service.dish.DishNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishes/names")
public class DishNameController {
    @Autowired
    private DishNameService dishNameService;

    @PostMapping("/save")
    public DishNameResp save(@RequestBody CreateDishNameReq dto) {
        return dishNameService.save(dto);
    }
}
