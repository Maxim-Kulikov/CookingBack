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
public class UpdateIngredientReq {
    @Min(1)
    @Max(30000)
    private Integer idIngredientType;

    @Size(min = 2, max = 45)
    private String ingredientName;

    @Min(0)
    @Max(30000)
    private Integer calories;

    @Min(0)
    @Max(30000)
    private Integer proteins;

    @Min(0)
    @Max(30000)
    private Integer fats;

    @Min(0)
    @Max(30000)
    private Integer carbohydrates;
}
