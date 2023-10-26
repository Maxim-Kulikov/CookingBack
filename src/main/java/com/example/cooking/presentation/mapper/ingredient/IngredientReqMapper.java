package com.example.cooking.presentation.mapper.ingredient;

import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientKindReq;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientReq;
import com.example.cooking.presentation.dto.ingredient.req.CreateIngredientTypeReq;
import com.example.cooking.presentation.dto.ingredient.req.CreateProductKindReq;
import com.example.cooking.data.model.postgres.ingredient.Ingredient;
import com.example.cooking.data.model.postgres.ingredient.IngredientKind;
import com.example.cooking.data.model.postgres.ingredient.IngredientType;
import com.example.cooking.data.model.postgres.ingredient.ProductKind;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngredientReqMapper {
    @Mapping(target = "id", ignore = true)
    IngredientKind toIngredientKind(CreateIngredientKindReq req, ProductKind productKind);

    @Mapping(target = "id", ignore = true)
    Ingredient toIngredient(CreateIngredientReq req, IngredientType ingredientType);

    @Mapping(target = "id", ignore = true)
    IngredientType toIngredientType(CreateIngredientTypeReq req, IngredientKind ingredientKind);

    @Mapping(target = "id", ignore = true)
    ProductKind toProductKind(CreateProductKindReq req);
}
