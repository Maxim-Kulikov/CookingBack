package com.example.cooking.presentation.controller.ingredient;

import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateIngredientTypeReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientResp;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.buisness.service.ingredient.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/ingredients/types")
public class IngredientTypeController {
    @Autowired
    private IngredientTypeService service;

    @PostMapping("/save")
    public IngredientTypeResp save(@RequestBody CreateIngredientTypeReq req) {
        return service.save(req);
    }

    @PatchMapping("/update/{id}")
    public IngredientTypeResp update(@PathVariable int id, @RequestBody UpdateIngredientTypeReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public IngredientTypeResp findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/ingredients")
    public List<IngredientResp> findAllIngredientsById(@PathVariable int id) {
        return service.findAllIngredientsById(id);
    }

    @PostMapping("")
    public List<IngredientTypeResp> findAllWithQuery(@RequestBody(required = false) Query query) {
        return service.findAllByQuery(query);
    }

}
