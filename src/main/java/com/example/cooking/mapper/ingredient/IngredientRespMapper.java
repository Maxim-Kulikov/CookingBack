package com.example.cooking.mapper.ingredient;

import com.example.cooking.dto.ingredient.resp.*;
import com.example.cooking.model.postgres.ingredient.Ingredient;
import com.example.cooking.model.postgres.ingredient.IngredientKind;
import com.example.cooking.model.postgres.ingredient.IngredientType;

import com.example.cooking.model.postgres.ingredient.ProductKind;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientRespMapper {
    @Mapping(target = "id", source = "ingredientKind.id")
    @Mapping(target = "kind", source = "ingredientKind.kind")
    @Mapping(target = "idProductKind", source = "productKind.id")
    @Mapping(target = "productKind", source = "productKind.name")
    IngredientKindResp toIngredientKindResp(IngredientKind ingredientKind);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "idProductKind", source = "ingredientType.ingredientKind.productKind.id")
    @Mapping(target = "productKind", source = "ingredientType.ingredientKind.productKind.name")
    @Mapping(target = "idIngredientType", source = "ingredientType.id")
    @Mapping(target = "ingredientType", source = "ingredientType.type")
    @Mapping(target = "idIngredientKind", source = "ingredientType.ingredientKind.id")
    @Mapping(target = "ingredientKind", source = "ingredientType.ingredientKind.kind")
    IngredientResp toIngredientResp(Ingredient ingredient);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "idIngredientKind", source = "ingredientKind.id")
    @Mapping(target = "kind", source = "ingredientKind.kind")
    @Mapping(target = "idProductKind", source = "ingredientKind.productKind.id")
    @Mapping(target = "productKind", source = "ingredientKind.productKind.name")
    IngredientTypeResp toIngredientTypeResp(IngredientType ingredientType);

    @Mapping(target = "kind", source = "name")
    ProductKindResp toProductKindResp(ProductKind productKind);

    List<ProductKindResp> toProductKindResps(List<ProductKind> productKinds);
    List<IngredientKindResp> toIngredientKindResps(List<IngredientKind> ingredientKindResps);

}
