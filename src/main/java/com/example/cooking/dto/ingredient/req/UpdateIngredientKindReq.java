package com.example.cooking.dto.ingredient.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateIngredientKindReq {
    @Size(min = 2, max = 45)
    private String kind;

    @Min(1)
    @Max(30000)
    private Integer idProductKind;
}
