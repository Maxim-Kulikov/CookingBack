package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.exception.ingredient.ProductKindIsExistedException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.model.postgres.ingredient.IngredientKind;
import com.example.cooking.service.ingredient.ProductKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/ingredients/product-kinds")
public class ProductKindController {
    @Autowired
    private ProductKindService service;

    @PostMapping("/save")
    public ProductKindResp save(@RequestBody CreateProductKindReq req) throws ProductKindIsExistedException {
        return service.save(req);
    }

    @PatchMapping("/update/{id}")
    public ProductKindResp update(@PathVariable final int id,
                                  @RequestBody UpdateProductKindReq req) throws ProductKindNotFoundException, ProductKindIsExistedException {
        return service.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final int id) throws ProductKindNotFoundException {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ProductKindResp findById(@PathVariable final int id) throws ProductKindNotFoundException {
        return service.findById(id);
    }

    @GetMapping("/{id}/ingredient-kinds")
    public List<IngredientKindResp> findAllIngredientKindsById(@PathVariable final int id) throws ProductKindNotFoundException {
        return service.findAllIngredientKinds(id);
    }
}
