package com.example.cooking.presentation.controller.ingredient;

import com.example.cooking.buisness.service.ingredient.ProductKindService;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.presentation.dto.ingredient.resp.ProductKindResp;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/product-kinds")
@RequiredArgsConstructor
public class ProductKindController {

    private final ProductKindService service;

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
