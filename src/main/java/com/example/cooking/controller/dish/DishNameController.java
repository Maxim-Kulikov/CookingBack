package com.example.cooking.controller.dish;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.dish.req.CreateDishNameReq;
import com.example.cooking.dto.dish.req.UpdateDishNameReq;
import com.example.cooking.dto.dish.resp.DishNameResp;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.service.dish.DishNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes/names")
public class DishNameController {
    @Autowired
    private DishNameService dishNameService;

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
