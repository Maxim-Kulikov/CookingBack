package com.example.cooking.buisness.service.ingredient.impl;

import com.example.cooking.buisness.service.ingredient.IngredientKindService;
import com.example.cooking.data.model.postgres.ingredient.IngredientKind;
import com.example.cooking.data.model.postgres.ingredient.ProductKind;
import com.example.cooking.data.repository.postgres.ingredient.IngredientKindRepository;
import com.example.cooking.exception.ingredient.IngredientKindIsExistedException;
import com.example.cooking.exception.ingredient.IngredientKindNotFoundException;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateIngredientKindReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientTypeResp;
import com.example.cooking.presentation.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.presentation.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientKindServiceImpl implements IngredientKindService {
    @Autowired
    private IngredientKindRepository repository;

    @Autowired
    private ProductKindServiceImpl productKindService;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

    @Transactional
    @Override
    public IngredientKindResp save(CreateIngredientKindReq req) {
        ifIngredientKindIsExistedThrowException(req.getKind());

        int idProductKind = req.getIdProductKind();
        ProductKind productKind = productKindService.getProductKindOrThrowException(idProductKind);

        IngredientKind ingredientKind = reqMapper.toIngredientKind(req, productKind);
        repository.save(ingredientKind);

        return respMapper.toIngredientKindResp(ingredientKind);
    }

    @Transactional
    @Override
    public IngredientKindResp update(Integer id, UpdateIngredientKindReq req) {
        IngredientKind ingredientKind = getIngredientKindOrThrowException(id);

        if (req.getIdProductKind() != null && !req.getIdProductKind().equals(ingredientKind.getProductKind().getId())) {
            ingredientKind.setProductKind(productKindService.getProductKindOrThrowException(req.getIdProductKind()));
        }

        if (!DataValidator.isNullOrEmpty(req.getKind()) && !req.getKind().equals(ingredientKind.getKind())) {
            ifIngredientKindIsExistedThrowException(req.getKind());
            ingredientKind.setKind(req.getKind());
        }

        repository.save(ingredientKind);
        return respMapper.toIngredientKindResp(ingredientKind);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        getIngredientKindOrThrowException(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public IngredientKindResp findById(Integer id) {
        return respMapper.toIngredientKindResp(getIngredientKindOrThrowException(id));
    }

    @Transactional
    @Override
    public List<IngredientTypeResp> findAllIngredientTypesById(Integer id) {
        return respMapper.toIngredientTypeResps(
                getIngredientKindOrThrowException(id).getIngredientTypes()
        );
    }

    @Transactional
    @Override
    public List<IngredientKindResp> findAllByQuery(Query query) {
        return respMapper.toIngredientKindResps(
                DataValidator.isObjectOrFieldNull(query) ? (List<IngredientKind>) repository.findAll()
                        : repository.findAllByKindContainsIgnoreCase(query.getQuery())
        );
    }

    protected void ifIngredientKindIsExistedThrowException(String kind) {
        Optional<IngredientKind> ingredientKind = repository.findByKindIgnoreCase(kind);
        if (ingredientKind.isPresent()) {
            throw new IngredientKindIsExistedException(ingredientKind.get().getId(), kind);
        }
    }

    protected IngredientKind getIngredientKindOrThrowException(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IngredientKindNotFoundException(id));
    }

}
