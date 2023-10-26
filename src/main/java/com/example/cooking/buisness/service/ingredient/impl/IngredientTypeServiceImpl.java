package com.example.cooking.buisness.service.ingredient.impl;

import com.example.cooking.buisness.service.ingredient.IngredientTypeService;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateIngredientTypeReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientResp;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.exception.ingredient.IngredientTypeIsExistedException;
import com.example.cooking.exception.ingredient.IngredientTypeNotFoundException;
import com.example.cooking.presentation.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.presentation.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.data.model.postgres.ingredient.IngredientKind;
import com.example.cooking.data.model.postgres.ingredient.IngredientType;
import com.example.cooking.data.repository.postgres.ingredient.IngredientTypeRepository;
import com.example.cooking.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientTypeServiceImpl implements IngredientTypeService {
    @Autowired
    private IngredientTypeRepository repository;

    @Autowired
    private IngredientKindServiceImpl ingredientKindService;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

    @Transactional
    @Override
    public IngredientTypeResp save(CreateIngredientTypeReq req) {
        ifIngredientTypeIsExistedThrowException(req.getType());

        int idIngredientKind = req.getIdIngredientKind();
        IngredientKind ingredientKind = ingredientKindService.getIngredientKindOrThrowException(idIngredientKind);

        IngredientType ingredientType = reqMapper.toIngredientType(req, ingredientKind);
        repository.save(ingredientType);

        return respMapper.toIngredientTypeResp(ingredientType);
    }

    @Transactional
    @Override
    public IngredientTypeResp update(Integer id, UpdateIngredientTypeReq req) {
        IngredientType ingredientType = getIngredientTypeOrThrowException(id);

        if (!DataValidator.isNullOrEmpty(req.getType()) && !req.getType().equals(ingredientType.getType())) {
            ifIngredientTypeIsExistedThrowException(req.getType());
            ingredientType.setType(req.getType());
        }

        if (req.getIdIngredientKind() != null &&
                !req.getIdIngredientKind().equals(ingredientType.getIngredientKind().getId())) {
            IngredientKind ingredientKind
                    = ingredientKindService.getIngredientKindOrThrowException(req.getIdIngredientKind());
            ingredientType.setIngredientKind(ingredientKind);
        }

        repository.save(ingredientType);
        return respMapper.toIngredientTypeResp(ingredientType);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        getIngredientTypeOrThrowException(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public IngredientTypeResp findById(Integer id) {
        return respMapper.toIngredientTypeResp(getIngredientTypeOrThrowException(id));
    }

    @Transactional
    @Override
    public List<IngredientResp> findAllIngredientsById(Integer id) {
        return respMapper.toIngredientResps(
                getIngredientTypeOrThrowException(id).getIngredients()
        );
    }

    @Transactional
    @Override
    public List<IngredientTypeResp> findAllByQuery(Query query) {
        return respMapper.toIngredientTypeResps(
                DataValidator.isObjectOrFieldNull(query) ? (List<IngredientType>) repository.findAll()
                        : repository.findAllByTypeContainsIgnoreCase(query.getQuery())
        );
    }

    protected IngredientType getIngredientTypeOrThrowException(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IngredientTypeNotFoundException(id));
    }

    protected void ifIngredientTypeIsExistedThrowException(String type) {
        Optional<IngredientType> ingredientType = repository.findByTypeIgnoreCase(type);
        if (ingredientType.isPresent()) {
            throw new IngredientTypeIsExistedException(ingredientType.get().getId(), type);
        }
    }

}
