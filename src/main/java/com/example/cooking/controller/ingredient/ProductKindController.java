package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.service.ingredient.ProductKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/ingredients/product-kinds")
public class ProductKindController {
    @Autowired
    private ProductKindService service;

    @PostMapping("/save")
    public ProductKindResp save(@RequestBody CreateProductKindReq req) {
        return service.save(req);
    }

    @PatchMapping("/update/{id}")
    public ProductKindResp update(@PathVariable int id,
                                  @RequestBody UpdateProductKindReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ProductKindResp findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/ingredient-kinds")
    public List<IngredientKindResp> findAllIngredientKindsById(@PathVariable int id) {
        return service.findAllIngredientKinds(id);
    }

    @PostMapping("")
    public List<ProductKindResp> findAllWithQuery(@RequestBody(required = false) Query query) {
        return service.findAllByQuery(query);
    }
}
