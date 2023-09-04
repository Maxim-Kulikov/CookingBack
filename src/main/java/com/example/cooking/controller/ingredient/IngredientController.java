package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.dto.ingredient.resp.IngredientResp;
import com.example.cooking.exception.ingredient.IngredientIsExistedException;
import com.example.cooking.exception.ingredient.IngredientNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeNotFoundException;
import com.example.cooking.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService service;

    @PostMapping("/save")
    public IngredientResp save(@Valid @RequestBody CreateIngredientReq req) throws IngredientTypeNotFoundException, IngredientIsExistedException {
        return service.save(req);
    }

    @GetMapping("/{id}")
    public IngredientResp findById(@Valid @PathVariable Integer id) throws IngredientNotFoundException {
        return service.findById(id);
    }

}
