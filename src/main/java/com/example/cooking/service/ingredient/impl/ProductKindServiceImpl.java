package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.exception.ingredient.ProductKindIsExistedException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.ProductKind;
import com.example.cooking.repository.postgres.ingredient.ProductKindRepository;
import com.example.cooking.service.ingredient.ProductKindService;
import com.example.cooking.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductKindServiceImpl implements ProductKindService {
    @Autowired
    private ProductKindRepository repository;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

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
