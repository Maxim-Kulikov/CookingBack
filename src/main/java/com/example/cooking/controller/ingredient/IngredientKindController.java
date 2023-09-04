package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.exception.ingredient.IngredientKindIsExistedException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.service.ingredient.IngredientKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ingredients/kinds")
public class IngredientKindController {
    @Autowired
    private IngredientKindService service;

    @PostMapping("/save")
    public IngredientKindResp save(@Valid @RequestBody CreateIngredientKindReq req) throws ProductKindNotFoundException, IngredientKindIsExistedException {
        return service.save(req);
    }
}
