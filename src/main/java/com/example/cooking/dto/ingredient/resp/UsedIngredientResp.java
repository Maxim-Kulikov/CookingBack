package com.example.cooking.dto.ingredient.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsedIngredientResp {
    private Integer idIngredient;
    private Integer weight;

    private Integer idType;
    private String type;

    private Integer idKind;
    private String kind;

    private Integer idProductKind;
    private String productKind;
}
