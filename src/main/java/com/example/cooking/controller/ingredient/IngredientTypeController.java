package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientTypeReq;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeIsExistedException;
import com.example.cooking.exception.ingredient.IngredientTypeNotFoundException;
import com.example.cooking.service.ingredient.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/ingredients/types")
public class IngredientTypeController {
    @Autowired
    private IngredientTypeService service;

    @PostMapping("/save")
    public IngredientTypeResp save(@RequestBody CreateIngredientTypeReq req) throws IngredientKindNotFoundException, IngredientTypeIsExistedException {
        return service.save(req);
    }

    @PatchMapping("/update/{id}")
    public IngredientTypeResp update(@PathVariable final int id,
                                     @RequestBody UpdateIngredientTypeReq req)
            throws IngredientKindNotFoundException, IngredientTypeNotFoundException, IngredientTypeIsExistedException {
        return service.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final int id) throws IngredientTypeNotFoundException {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public IngredientTypeResp findById(@PathVariable final int id) throws IngredientTypeNotFoundException {
        return service.findById(id);
    }

}
