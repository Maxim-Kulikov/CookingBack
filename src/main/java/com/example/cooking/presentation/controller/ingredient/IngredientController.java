package com.example.cooking.presentation.controller.ingredient;

import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.presentation.dto.ingredient.req.IngredientFilterReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateIngredientReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientResp;
import com.example.cooking.buisness.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/save")
    public IngredientResp save(@RequestBody CreateIngredientReq req) {
        return ingredientService.save(req);
    }

    @GetMapping("/{id}")
    public IngredientResp findById(@PathVariable int id) {
        return ingredientService.findById(id);
    }

    @PatchMapping("/update/{id}")
    public IngredientResp update(@PathVariable int id,
                                 @RequestBody UpdateIngredientReq req) {
        return ingredientService.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        ingredientService.delete(id);
    }

    @PostMapping("")
    public List<IngredientResp> findAllByFilter(@RequestBody(required = false) IngredientFilterReq filter) {
        return ingredientService.findAllWithFilter(filter);
    }
}
