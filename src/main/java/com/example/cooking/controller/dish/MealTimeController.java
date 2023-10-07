package com.example.cooking.controller.dish;

import com.example.cooking.dto.dish.req.CreateMealTimeReq;
import com.example.cooking.dto.dish.resp.MealTimeResp;
import com.example.cooking.service.dish.MealTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meal-times")
public class MealTimeController {
    @Autowired
    private MealTimeService mealTimeService;

    @PostMapping("/save")
    public MealTimeResp save(@RequestBody CreateMealTimeReq dto) {
        return mealTimeService.save(dto);
    }
}
