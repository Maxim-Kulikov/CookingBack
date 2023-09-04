package com.example.cooking.dto.ingredient.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientKindResp {
    private Integer id;
    private String kind;

    private Integer idProductKind;
    private String productKind;
}
