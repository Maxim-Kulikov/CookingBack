package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.exception.ingredient.ProductKindIsExistedException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.ProductKind;
import com.example.cooking.repository.postgres.ingredient.ProductKindDao;
import com.example.cooking.service.ingredient.ProductKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductKindServiceImpl implements ProductKindService {
    @Autowired
    private ProductKindDao dao;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

    @Override
    public ProductKindResp save(CreateProductKindReq req) throws ProductKindIsExistedException {
        Optional<ProductKind> productKindOptional = dao.findByName(req.getName());
        if (productKindOptional.isPresent()) {
            throw new ProductKindIsExistedException(productKindOptional.get().getId(), req.getName());
        }

        ProductKind productKind = reqMapper.toProductKind(req);
        dao.save(productKind);

        return respMapper.toProductKindResp(productKind);
    }


}
