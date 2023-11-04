package com.example.cooking.presentation.controller.ingredient;

import com.example.cooking.buisness.service.ingredient.IngredientKindService;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateIngredientKindReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientTypeResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient-kinds")
@RequiredArgsConstructor
public class IngredientKindController {
    private final IngredientKindService service;

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
