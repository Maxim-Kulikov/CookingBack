package com.example.cooking.presentation.controller.dish;

import com.example.cooking.presentation.dto.dish.req.CreateDishReq;
import com.example.cooking.presentation.dto.dish.req.DishFilterReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishReq;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import com.example.cooking.buisness.service.dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping("/save")
    public DishResp save(@RequestBody CreateDishReq dto) {
        return dishService.save(dto);
    }

    @PatchMapping("/update/{id}")
    public DishResp update(@PathVariable Integer id, @RequestBody UpdateDishReq dto) {
        return dishService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        dishService.delete(id);
    }

    @GetMapping("/{id}")
    public DishResp findById(@PathVariable Integer id) {
        return dishService.findById(id);
    }

    @PostMapping("")
    public List<DishResp> findAllByFilter(@RequestBody(required = false) DishFilterReq dto) {
        return dishService.findAllByFilter(dto);
    }
}
