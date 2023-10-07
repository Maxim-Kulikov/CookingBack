package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.service.ingredient.IngredientKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients/kinds")
public class IngredientKindController {
    @Autowired
    private IngredientKindService service;

    @PostMapping("/save")
    public IngredientKindResp save(@RequestBody CreateIngredientKindReq req) {
        return service.save(req);
    }

    @PatchMapping("/update/{id}")
    public IngredientKindResp update(@PathVariable int id, @RequestBody UpdateIngredientKindReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public IngredientKindResp findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/ingredient-types")
    public List<IngredientTypeResp> findAllIngredientTypesById(@PathVariable int id) {
        return service.findAllIngredientTypesById(id);
    }

    @PostMapping("")
    public List<IngredientKindResp> findAllWithQuery(@RequestBody(required = false) Query query) {
        return service.findAllByQuery(query);
    }
}
