package com.example.cooking.service.ingredient.impl;

import com.example.cooking.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.dto.ingredient.req.UpdateProductKindReq;
import com.example.cooking.dto.ingredient.resp.IngredientKindResp;
import com.example.cooking.dto.ingredient.resp.ProductKindResp;
import com.example.cooking.exception.ingredient.ProductKindIsExistedException;
import com.example.cooking.exception.ingredient.ProductKindNotFoundException;
import com.example.cooking.mapper.ingredient.IngredientReqMapper;
import com.example.cooking.mapper.ingredient.IngredientRespMapper;
import com.example.cooking.model.postgres.ingredient.ProductKind;
import com.example.cooking.repository.postgres.ingredient.ProductKindDao;
import com.example.cooking.service.ingredient.ProductKindService;
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
public class ProductKindServiceImpl implements ProductKindService {
    @Autowired
    private ProductKindDao dao;

    @Autowired
    private IngredientReqMapper reqMapper;

    @Autowired
    private IngredientRespMapper respMapper;

    @Override
    public ProductKindResp save(CreateProductKindReq req) throws ProductKindIsExistedException {
        Optional<ProductKind> productKindOptional = dao.findByName(req.getName());
        if (productKindOptional.isPresent()) {
            throw new ProductKindIsExistedException(productKindOptional.get().getId(), req.getName());
        }

        ProductKind productKind = reqMapper.toProductKind(req);
        dao.save(productKind);

        return respMapper.toProductKindResp(productKind);
    }

    @Override
    public ProductKindResp update(@NonNull @Min(0) @Max(30000) Integer id, UpdateProductKindReq req) throws ProductKindIsExistedException, ProductKindNotFoundException {
        ProductKind productKind = getProductKindOrThrowException(id);

        String oldKind = productKind.getName();
        String newKind = req.getName();
        if (newKind != null && !newKind.equals(oldKind)) {
            ifProductKindIsExistedThrowException(newKind);
            productKind.setName(newKind);
        }

        dao.save(productKind);
        return respMapper.toProductKindResp(productKind);
    }

    @Override
    public void delete(@NonNull @Min(0) @Max(30000) Integer id) throws ProductKindNotFoundException {
        getProductKindOrThrowException(id);
        dao.deleteById(id);
    }

    @Override
    public ProductKindResp findById(@NonNull @Min(0) @Max(30000) Integer id) throws ProductKindNotFoundException {
        return respMapper.toProductKindResp(getProductKindOrThrowException(id));
    }

    @Override
    public List<IngredientKindResp> findAllIngredientKinds(@NonNull @Min(0) @Max(30000) Integer id) throws ProductKindNotFoundException {
        return respMapper.toIngredientKindResps(
                getProductKindOrThrowException(id).getIngredientKinds()
        );
    }

    @Override
    public List<ProductKindResp> findAll() {
        return respMapper.toProductKindResps(
                (List<ProductKind>) dao.findAll()
        );
    }

    private ProductKind getProductKindOrThrowException(int id) throws ProductKindNotFoundException {
        return dao.findById(id)
                .orElseThrow(() -> new ProductKindNotFoundException(id));
    }

    private void ifProductKindIsExistedThrowException(String kind) throws ProductKindIsExistedException {
        Optional<ProductKind> productKind = dao.findByName(kind);
        if (productKind.isPresent()) {
            throw new ProductKindIsExistedException(productKind.get().getId(), kind);
        }
    }


}
