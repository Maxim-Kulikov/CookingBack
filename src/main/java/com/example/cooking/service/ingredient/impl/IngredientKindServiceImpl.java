package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.exception.ingredient.IngredientKindIsExistedException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.IngredientKind;
import com.example.cooking.model.postgres.ingredient.ProductKind;
import com.example.cooking.repository.postgres.ingredient.IngredientKindDao;
import com.example.cooking.repository.postgres.ingredient.ProductKindDao;
import com.example.cooking.service.ingredient.IngredientKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientKindServiceImpl implements IngredientKindService {
    @Autowired
    private IngredientKindDao ingredientKindDao;

    @Autowired
    private ProductKindDao productKindDao;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

    public IngredientKindResp save(CreateIngredientKindReq req) throws ProductKindNotFoundException, IngredientKindIsExistedException {
        Optional<IngredientKind> ingredientKindOptional = ingredientKindDao.findByKind(req.getKind());

        if (ingredientKindOptional.isPresent()) {
            throw new IngredientKindIsExistedException(ingredientKindOptional.get().getId(), req.getKind());
        }

        int idProductKind = req.getIdProductKind();
        ProductKind productKind = productKindDao.findById(idProductKind)
                .orElseThrow(() -> new ProductKindNotFoundException(idProductKind));

        IngredientKind ingredientKind = reqMapper.toIngredientKind(req, productKind);
        ingredientKindDao.save(ingredientKind);

        return respMapper.toIngredientKindResp(ingredientKind);
    }


}
