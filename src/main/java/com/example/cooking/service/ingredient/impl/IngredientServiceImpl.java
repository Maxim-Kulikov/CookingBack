package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientReq;
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
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Validated
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
        int idIngredientType = req.getIdIngredientType();
        IngredientType ingredientType = getIngredientTypeOrThrowException(idIngredientType);

        Ingredient ingredient = reqMapper.toIngredient(req, ingredientType);
        ingredientDao.save(ingredient);

        return respMapper.toIngredientResp(ingredient);
    }

    @Override
    public IngredientResp update(@NonNull @Min(0) @Max(30000) Integer id, UpdateIngredientReq req) throws IngredientTypeNotFoundException, IngredientNotFoundException {
        Ingredient ingredient = getIngredientOrThrowException(id);

        Integer newIdIngredientType = req.getIdIngredientType();
        Integer oldIdIngredientType = ingredient.getIngredientType().getId();
        if (newIdIngredientType != null && !newIdIngredientType.equals(oldIdIngredientType)) {
            ingredient.setIngredientType(getIngredientTypeOrThrowException(newIdIngredientType));
        }

        String ingredientName = req.getIngredientName();
        if (ingredientName != null && !ingredientName.isBlank()) {
            ingredient.setIngredientName(ingredientName);
        }

        Integer calories = req.getCalories();
        if (calories != null) {
            ingredient.setCalories(calories);
        }

        Integer proteins = req.getProteins();
        if (proteins != null) {
            ingredient.setProteins(proteins);
        }

        Integer fats = req.getFats();
        if (fats != null) {
            ingredient.setFats(fats);
        }

        Integer carbohydrates = req.getCarbohydrates();
        if (carbohydrates != null) {
            ingredient.setCarbohydrates(carbohydrates);
        }

        ingredientDao.save(ingredient);

        return respMapper.toIngredientResp(ingredient);
    }

    @Override
    public void delete(@NonNull @Min(0) @Max(30000) Integer id) throws IngredientNotFoundException {
        getIngredientOrThrowException(id);
        ingredientDao.deleteById(id);
    }

    @Override
    public IngredientResp findById(@NonNull @Min(0) @Max(30000) Integer id) throws IngredientNotFoundException {
        Ingredient ingredient = ingredientDao.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));

        return respMapper.toIngredientResp(ingredient);
    }

    private Ingredient getIngredientOrThrowException(int id) throws IngredientNotFoundException {
        return ingredientDao.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    private IngredientType getIngredientTypeOrThrowException(int id) throws IngredientTypeNotFoundException {
        return ingredientTypeDao.findById(id)
                .orElseThrow(() -> new IngredientTypeNotFoundException(id));
    }

}
