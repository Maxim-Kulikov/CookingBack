package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientReq;
import com.example.cooking.dto.ingredient.resp.IngredientResp;
import com.example.cooking.service.ingredient.IngredientService;
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
    public List<IngredientResp> findAllWithQuery(@RequestBody(required = false) Query query) {
        return ingredientService.findAllByQuery(query);
    }
}
