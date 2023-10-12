package com.example.cooking.presentation.dto.ingredient.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateIngredientReq {
    @NotNull
    @Min(1)
    @Max(30000)
    private Integer idIngredientType;

    @NotBlank
    @Size(min = 2, max = 45)
    private String ingredientName;

    @NotNull
    @Min(0)
    @Max(30000)
    private Double calories;

    @NotNull
    @Min(0)
    @Max(30000)
    private Double proteins;

    @NotNull
    @Min(0)
    @Max(30000)
    private Double fats;

    @NotNull
    @Min(0)
    @Max(30000)
    private Double carbohydrates;
}
