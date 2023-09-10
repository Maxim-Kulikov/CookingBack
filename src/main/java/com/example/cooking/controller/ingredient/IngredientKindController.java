package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.exception.ingredient.IngredientKindIsExistedException;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.service.ingredient.IngredientKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients/kinds")
public class IngredientKindController {
    @Autowired
    private IngredientKindService service;

    @PostMapping("/save")
    public IngredientKindResp save(@RequestBody CreateIngredientKindReq req) throws ProductKindNotFoundException, IngredientKindIsExistedException {
        return service.save(req);
    }

    @PatchMapping("/update/{id}")
    public IngredientKindResp update(@PathVariable final int id,
                                     @RequestBody UpdateIngredientKindReq req)
            throws ProductKindNotFoundException, IngredientKindNotFoundException, IngredientKindIsExistedException {
        return service.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final int id) throws IngredientKindNotFoundException {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public IngredientKindResp findById(@PathVariable final int id) throws IngredientKindNotFoundException {
        return service.findById(id);
    }
}
