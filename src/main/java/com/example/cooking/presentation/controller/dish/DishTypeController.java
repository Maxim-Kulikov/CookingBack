package com.example.cooking.presentation.controller.dish;

import com.example.cooking.buisness.service.dish.DishTypeService;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.dish.req.CreateDishTypeReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishTypeReq;
import com.example.cooking.presentation.dto.dish.resp.DishNameResp;
import com.example.cooking.presentation.dto.dish.resp.DishTypeResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish-types")
@RequiredArgsConstructor
public class DishTypeController {
    private final DishTypeService dishTypeService;

    @PostMapping("/save")
    public DishTypeResp save(@RequestBody CreateDishTypeReq dto) {
        return dishTypeService.save(dto);
    }

    @PatchMapping("/update/{id}")
    public DishTypeResp update(@PathVariable Integer id, @RequestBody UpdateDishTypeReq dto) {
        return dishTypeService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        dishTypeService.delete(id);
    }

    @GetMapping("/{id}")
    public DishTypeResp findById(@PathVariable Integer id) {
        return dishTypeService.findById(id);
    }

    @PostMapping("")
    public List<DishTypeResp> findAllByQuery(@RequestBody Query query) {
        return dishTypeService.findAllByQuery(query);
    }

    @GetMapping("/{id}/dish-names")
    public List<DishNameResp> findAllDishNamesById(@PathVariable Integer id) {
        return dishTypeService.findAllDishNamesById(id);
    }
}
