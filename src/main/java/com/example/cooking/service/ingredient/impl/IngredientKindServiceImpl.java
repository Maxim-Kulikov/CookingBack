package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.exception.ingredient.IngredientKindIsExistedException;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.IngredientKind;
import com.example.cooking.model.postgres.ingredient.ProductKind;
import com.example.cooking.repository.postgres.ingredient.IngredientKindDao;
import com.example.cooking.repository.postgres.ingredient.ProductKindDao;
import com.example.cooking.service.ingredient.IngredientKindService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Optional;

@Validated
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

    @Override
    public IngredientKindResp save(CreateIngredientKindReq req) throws ProductKindNotFoundException, IngredientKindIsExistedException {
        ifIngredientKindIsExistedThrowException(req.getKind());

        int idProductKind = req.getIdProductKind();
        ProductKind productKind = getProductKindOrThrowException(idProductKind);

        IngredientKind ingredientKind = reqMapper.toIngredientKind(req, productKind);
        ingredientKindDao.save(ingredientKind);

        return respMapper.toIngredientKindResp(ingredientKind);
    }

    @Override
    public IngredientKindResp update(@NonNull @Min(0) @Max(30000) Integer id, UpdateIngredientKindReq req) throws IngredientKindIsExistedException, ProductKindNotFoundException, IngredientKindNotFoundException {
        IngredientKind ingredientKind = getIngredientKindOrThrowException(id);

        Integer newIdProductKind = req.getIdProductKind();
        Integer oldIdProductKind = ingredientKind.getProductKind().getId();
        if (newIdProductKind != null && !newIdProductKind.equals(oldIdProductKind)) {
            ingredientKind.setProductKind(getProductKindOrThrowException(newIdProductKind));
        }

        String newKind = req.getKind();
        String oldKind = ingredientKind.getKind();
        if (newKind != null && !newKind.equals(oldKind)) {
            ifIngredientKindIsExistedThrowException(newKind);
            ingredientKind.setKind(newKind);
        }

        ingredientKindDao.save(ingredientKind);
        return respMapper.toIngredientKindResp(ingredientKind);
    }

    @Override
    public void delete(@NonNull @Min(0) @Max(30000) Integer id) throws IngredientKindNotFoundException {
        getIngredientKindOrThrowException(id);
        ingredientKindDao.deleteById(id);
    }

    @Override
    public IngredientKindResp findById(@NonNull @Min(0) @Max(30000) Integer id) throws IngredientKindNotFoundException {
        return respMapper.toIngredientKindResp(getIngredientKindOrThrowException(id));
    }

    private void ifIngredientKindIsExistedThrowException(String kind) throws IngredientKindIsExistedException {
        Optional<IngredientKind> ingredientKindOptional = ingredientKindDao.findByKind(kind);

        if (ingredientKindOptional.isPresent()) {
            throw new IngredientKindIsExistedException(ingredientKindOptional.get().getId(), kind);
        }
    }

    private IngredientKind getIngredientKindOrThrowException(Integer id) throws IngredientKindNotFoundException {
        return ingredientKindDao.findById(id)
                .orElseThrow(() -> new IngredientKindNotFoundException(id));
    }

    private ProductKind getProductKindOrThrowException(Integer id) throws ProductKindNotFoundException {
        return productKindDao.findById(id)
                .orElseThrow(() -> new ProductKindNotFoundException(id));
    }

}
