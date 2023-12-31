package com.example.cooking.buisness.service.ingredient.impl;

import com.example.cooking.buisness.service.ingredient.ProductKindService;
import com.example.cooking.data.model.postgres.ingredient.ProductKind;
import com.example.cooking.data.repository.postgres.ingredient.ProductKindRepository;
import com.example.cooking.exception.ingredient.ProductKindIsExistedException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.presentation.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.presentation.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.presentation.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.presentation.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.presentation.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.util.DataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductKindServiceImpl implements ProductKindService {
    private final ProductKindRepository repository;
    private final IngredientReqMapper reqMapper;
    private final IngredientRespMapper respMapper;

    @Transactional
    @Override
    public ProductKindResp save(CreateProductKindReq req) {
        ifProductKindIsExistedThrowException(req.getName());

        ProductKind productKind = reqMapper.toProductKind(req);
        repository.save(productKind);

        return respMapper.toProductKindResp(productKind);
    }

    @Transactional
    @Override
    public ProductKindResp update(Integer id, UpdateProductKindReq req) {
        ProductKind productKind = getProductKindOrThrowException(id);

        if (!DataValidator.isNullOrEmpty(req.getName()) && !req.getName().equals(productKind.getName())) {
            ifProductKindIsExistedThrowException(req.getName());
            productKind.setName(req.getName());
        }

        repository.save(productKind);

        return respMapper.toProductKindResp(productKind);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        getProductKindOrThrowException(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public ProductKindResp findById(Integer id) {
        return respMapper.toProductKindResp(getProductKindOrThrowException(id));
    }

    @Transactional
    @Override
    public List<IngredientKindResp> findAllIngredientKinds(Integer id) {
        return respMapper.toIngredientKindResps(
                getProductKindOrThrowException(id).getIngredientKinds()
        );
    }

    @Transactional
    @Override
    public List<ProductKindResp> findAllByQuery(Query query) {
        return respMapper.toProductKindResps(
                DataValidator.isObjectOrFieldNull(query) ? (List<ProductKind>) repository.findAll()
                        : repository.findAllByNameContainsIgnoreCase(query.getQuery())
        );
    }

    protected ProductKind getProductKindOrThrowException(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductKindNotFoundException(id));
    }

    protected void ifProductKindIsExistedThrowException(String kind) {
        Optional<ProductKind> productKind = repository.findByNameIgnoreCase(kind);
        if (productKind.isPresent()) {
            throw new ProductKindIsExistedException(productKind.get().getId(), kind);
        }
    }

}
