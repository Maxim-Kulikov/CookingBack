package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientReq;
import com.example.cooking.dto.ingredient.resp.IngredientResp;
import com.example.cooking.exception.ingredient.IngredientNotFoundException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.Ingredient;
import com.example.cooking.model.postgres.ingredient.IngredientType;
import com.example.cooking.repository.postgres.ingredient.IngredientRepository;
import com.example.cooking.service.ingredient.IngredientService;
import com.example.cooking.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository repository;

    @Autowired
    private IngredientTypeServiceImpl ingredientTypeService;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

    @Transactional
    @Override
    public IngredientResp save(CreateIngredientReq req) {
        int idIngredientType = req.getIdIngredientType();
        IngredientType ingredientType = ingredientTypeService.getIngredientTypeOrThrowException(idIngredientType);

        Ingredient ingredient = reqMapper.toIngredient(req, ingredientType);
        repository.save(ingredient);

        return respMapper.toIngredientResp(ingredient);
    }

    @Transactional
    @Override
    public IngredientResp update(Integer id, UpdateIngredientReq req) {
        Ingredient ingredient = getIngredientOrThrowException(id);

        if (req.getIdIngredientType() != null && !req.getIdIngredientType().equals(ingredient.getIngredientType().getId())) {
            ingredient.setIngredientType(ingredientTypeService.getIngredientTypeOrThrowException(req.getIdIngredientType()));
        }

        if (!DataValidator.isNullOrEmpty(req.getIngredientName())) {
            ingredient.setIngredientName(req.getIngredientName());
        }

        if (req.getCalories() != null) {
            ingredient.setCalories(req.getCalories());
        }

        if (req.getProteins() != null) {
            ingredient.setProteins(req.getProteins());
        }

        if (req.getFats() != null) {
            ingredient.setFats(req.getFats());
        }

        if (req.getCarbohydrates() != null) {
            ingredient.setCarbohydrates(req.getCarbohydrates());
        }

        repository.save(ingredient);
        return respMapper.toIngredientResp(ingredient);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        getIngredientOrThrowException(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public IngredientResp findById(Integer id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));

        return respMapper.toIngredientResp(ingredient);
    }

    @Transactional
    @Override
    public List<IngredientResp> findAllByQuery(Query query) {
        return respMapper.toIngredientResps(
                DataValidator.isObjectOrFieldNull(query) ? (List<Ingredient>) repository.findAll()
                        : repository.findAllByIngredientNameContainsIgnoreCase(query.getQuery())
        );
    }

    public Ingredient getIngredientOrThrowException(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

}
