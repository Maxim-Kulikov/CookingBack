package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientReq;
import com.example.cooking.dto.ingredient.resp.IngredientResp;
import com.example.cooking.exception.ingredient.IngredientIsExistedException;
import com.example.cooking.exception.ingredient.IngredientNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeNotFoundException;
import com.example.cooking.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService service;

    @PostMapping("/save")
    public IngredientResp save(@RequestBody CreateIngredientReq req) throws IngredientTypeNotFoundException, IngredientIsExistedException {
        return service.save(req);
    }

    @GetMapping("/{id}")
    public IngredientResp findById(@PathVariable final int id) throws IngredientNotFoundException {
        return service.findById(id);
    }

    @PatchMapping("/update/{id}")
    public IngredientResp update(@PathVariable final int id,
                                 @RequestBody UpdateIngredientReq req) throws IngredientNotFoundException, IngredientTypeNotFoundException {
        return service.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final int id) throws IngredientNotFoundException {
        service.delete(id);
    }

}
