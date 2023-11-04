package com.example.cooking.presentation.controller.dish;

import com.example.cooking.buisness.service.dish.DishNameService;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.dish.req.CreateDishNameReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishNameReq;
import com.example.cooking.presentation.dto.dish.resp.DishNameResp;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish-names")
@RequiredArgsConstructor
public class DishNameController {
    private final DishNameService dishNameService;

    @PostMapping("/save")
    public DishNameResp save(@RequestBody CreateDishNameReq dto) {
        return dishNameService.save(dto);
    }

    @PatchMapping("/update/{id}")
    public DishNameResp update(@PathVariable Integer id, @RequestBody UpdateDishNameReq dto) {
        return dishNameService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        dishNameService.delete(id);
    }

    @GetMapping("/{id}")
    public DishNameResp findById(@PathVariable Integer id) {
        return dishNameService.findById(id);
    }

    @PostMapping("")
    public List<DishNameResp> findAllByQuery(@RequestBody Query query) {
        return dishNameService.findAllByQuery(query);
    }

    @GetMapping("/{id}/dishes")
    public List<DishResp> findAllDishesById(@PathVariable Integer id) {
        return dishNameService.findAllDishesById(id);
    }
}
