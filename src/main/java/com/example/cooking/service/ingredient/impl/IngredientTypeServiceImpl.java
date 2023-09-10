package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.dto.ingredient.req.UpdateIngredientTypeReq;
import com.example.cooking.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.exception.ingredient.IngredientTypeIsExistedException;
import com.example.cooking.exception.ingredient.IngredientTypeNotFoundException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.IngredientKind;
import com.example.cooking.model.postgres.ingredient.IngredientType;
import com.example.cooking.repository.postgres.ingredient.IngredientKindDao;
import com.example.cooking.repository.postgres.ingredient.IngredientTypeDao;
import com.example.cooking.service.ingredient.IngredientTypeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Validated
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
        ifIngredientTypeIsExistedThrowException(req.getType());

        int idIngredientKind = req.getIdIngredientKind();
        IngredientKind ingredientKind = ingredientKindDao.findById(idIngredientKind)
                .orElseThrow(() -> new IngredientKindNotFoundException(idIngredientKind));

        IngredientType ingredientType = reqMapper.toIngredientType(req, ingredientKind);
        ingredientTypeDao.save(ingredientType);

        return respMapper.toIngredientTypeResp(ingredientType);
    }

    @Override
    public IngredientTypeResp update(@NonNull @Min(0) @Max(30000) Integer id, UpdateIngredientTypeReq req) throws IngredientTypeNotFoundException, IngredientTypeIsExistedException, IngredientKindNotFoundException {
        IngredientType ingredientType = getIngredientTypeOrThrowException(id);

        String newType = req.getType();
        String oldType = ingredientType.getType();
        if (newType != null && !newType.isBlank() && !newType.equals(oldType)) {
            ifIngredientTypeIsExistedThrowException(newType);
            ingredientType.setType(newType);
        }

        Integer newIdIngredientKind = req.getIdIngredientKind();
        Integer oldIdIngredientKind = ingredientType.getIngredientKind().getId();
        if (newIdIngredientKind != null && !newIdIngredientKind.equals(oldIdIngredientKind)) {
            IngredientKind ingredientKind = getIngredientKindOrThrowException(newIdIngredientKind);
            ingredientType.setIngredientKind(ingredientKind);
        }

        ingredientTypeDao.save(ingredientType);
        return respMapper.toIngredientTypeResp(ingredientType);
    }

    @Override
    public void delete(@NonNull @Min(0) @Max(30000) Integer id) throws IngredientTypeNotFoundException {
        getIngredientTypeOrThrowException(id);
        ingredientTypeDao.deleteById(id);
    }

    @Override
    public IngredientTypeResp findById(@NonNull @Min(0) @Max(30000) Integer id) throws IngredientTypeNotFoundException {
        return respMapper.toIngredientTypeResp(getIngredientTypeOrThrowException(id));
    }

    /*public List<IngredientTypeResp> findAll() {
        return respMapper.(List<IngredientTypeResp>) ingredientTypeDao.findAll()
    }
*/
    private IngredientType getIngredientTypeOrThrowException(int id) throws IngredientTypeNotFoundException {
        return ingredientTypeDao.findById(id)
                .orElseThrow(() -> new IngredientTypeNotFoundException(id));
    }

    private IngredientKind getIngredientKindOrThrowException(int id) throws IngredientKindNotFoundException {
        return ingredientKindDao.findById(id)
                .orElseThrow(() -> new IngredientKindNotFoundException(id));
    }

    private void ifIngredientTypeIsExistedThrowException(String type) throws IngredientTypeIsExistedException {
        Optional<IngredientType> ingredientTypeOptional = ingredientTypeDao.findByType(type);
        if (ingredientTypeOptional.isPresent()) {
            throw new IngredientTypeIsExistedException(ingredientTypeOptional.get().getId(), type);
        }
    }
}
