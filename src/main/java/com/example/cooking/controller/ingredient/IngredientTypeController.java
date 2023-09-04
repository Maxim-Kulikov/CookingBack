package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeIsExistedException;
import com.example.cooking.service.ingredient.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ingredients/types")
public class IngredientTypeController {
    @Autowired
    private IngredientTypeService service;

    @PostMapping("/save")
    public IngredientTypeResp save(@Valid @RequestBody CreateIngredientTypeReq req) throws IngredientKindNotFoundException, IngredientTypeIsExistedException {
        return service.save(req);
    }
}
