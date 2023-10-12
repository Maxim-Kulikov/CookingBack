package com.example.cooking.presentation.controller.dish;

import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.dish.req.CreateMealTimeReq;
import com.example.cooking.presentation.dto.dish.req.UpdateMealTimeReq;
import com.example.cooking.presentation.dto.dish.resp.DishTypeResp;
import com.example.cooking.presentation.dto.dish.resp.MealTimeResp;
import com.example.cooking.buisness.service.dish.MealTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal-times")
public class MealTimeController {
    @Autowired
    private MealTimeService mealTimeService;

    @PostMapping("/save")
    public MealTimeResp save(@RequestBody CreateMealTimeReq dto) {
        return mealTimeService.save(dto);
    }

    @PatchMapping("/update/{id}")
    public MealTimeResp update(@PathVariable Integer id, @RequestBody UpdateMealTimeReq dto) {
        return mealTimeService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        mealTimeService.delete(id);
    }

    @GetMapping("/{id}")
    public MealTimeResp findById(@PathVariable Integer id) {
        return mealTimeService.findById(id);
    }

    @PostMapping("")
    public List<MealTimeResp> findAllByQuery(@RequestBody Query query) {
        return mealTimeService.findAllByQuery(query);
    }

    @GetMapping("/{id}/dish-types")
    public List<DishTypeResp> findAllDishTypesById(@PathVariable Integer id) {
        return mealTimeService.findAllDishTypesById(id);
    }


}
