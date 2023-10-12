package com.example.cooking.presentation.dto.ingredient.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientTypeResp {
    private Integer id;
    private String type;

    private Integer idIngredientKind;
    private String kind;

    private Integer idProductKind;
    private String productKind;
}
