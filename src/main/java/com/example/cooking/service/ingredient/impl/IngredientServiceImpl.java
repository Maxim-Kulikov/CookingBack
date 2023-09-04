package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.dto.ingredient.resp.IngredientResp;
import com.example.cooking.exception.ingredient.IngredientIsExistedException;
import com.example.cooking.exception.ingredient.IngredientNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeNotFoundException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.Ingredient;
import com.example.cooking.model.postgres.ingredient.IngredientType;
import com.example.cooking.repository.postgres.ingredient.IngredientDao;
import com.example.cooking.repository.postgres.ingredient.IngredientTypeDao;
import com.example.cooking.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private IngredientTypeDao ingredientTypeDao;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

    @Override
    public IngredientResp save(CreateIngredientReq req) throws IngredientTypeNotFoundException, IngredientIsExistedException {
        Optional<Ingredient> ingredientOptional = ingredientDao.findByIngredientName(req.getIngredientName());
        if (ingredientOptional.isPresent()) {
            throw new IngredientIsExistedException(ingredientOptional.get().getId(), req.getIngredientName());
        }

        int idIngredientType = req.getIdIngredientType();
        IngredientType ingredientType = ingredientTypeDao.findById(idIngredientType)
                .orElseThrow(() -> new IngredientTypeNotFoundException(idIngredientType));

        Ingredient ingredient = reqMapper.toIngredient(req, ingredientType);
        ingredientDao.save(ingredient);

        return respMapper.toIngredientResp(ingredient);
    }

    @Override
    public IngredientResp findById(int id) throws IngredientNotFoundException {
        Ingredient ingredient = ingredientDao.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));

        return respMapper.toIngredientResp(ingredient);
    }


}
