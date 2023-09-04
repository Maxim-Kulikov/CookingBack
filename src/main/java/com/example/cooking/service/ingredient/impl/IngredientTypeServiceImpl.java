package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeIsExistedException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.IngredientKind;
import com.example.cooking.model.postgres.ingredient.IngredientType;
import com.example.cooking.repository.postgres.ingredient.IngredientKindDao;
import com.example.cooking.repository.postgres.ingredient.IngredientTypeDao;
import com.example.cooking.service.ingredient.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientTypeServiceImpl implements IngredientTypeService {
    @Autowired
    private IngredientTypeDao ingredientTypeDao;

    @Autowired
    private IngredientKindDao ingredientKindDao;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

    @Override
    public IngredientTypeResp save(CreateIngredientTypeReq req) throws IngredientKindNotFoundException, IngredientTypeIsExistedException {
        Optional<IngredientType> ingredientTypeOptional = ingredientTypeDao.findByType(req.getType());
        if (ingredientTypeOptional.isPresent()) {
            throw new IngredientTypeIsExistedException(ingredientTypeOptional.get().getId(), req.getType());
        }

        int idIngredientKind = req.getIdIngredientKind();
        IngredientKind ingredientKind = ingredientKindDao.findById(idIngredientKind)
                .orElseThrow(() -> new IngredientKindNotFoundException(idIngredientKind));

        IngredientType ingredientType = reqMapper.toIngredientType(req, ingredientKind);
        ingredientTypeDao.save(ingredientType);

        return respMapper.toIngredientTypeResp(ingredientType);
    }
}
