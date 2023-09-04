package com.example.cooking.controller.ingredient;

import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.exception.ingredient.ProductKindIsExistedException;
import com.example.cooking.service.ingredient.ProductKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ingredients/product-kind")
public class ProductKindController {
    @Autowired
    private ProductKindService service;

    @PostMapping("/save")
    public ProductKindResp save(@Valid @RequestBody CreateProductKindReq req) throws ProductKindIsExistedException {
        return service.save(req);
    }
}
