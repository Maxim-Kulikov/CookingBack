package com.example.cooking.presentation.dto.ingredient.req;

import com.example.cooking.buisness.enums.SortOrder;
import com.example.cooking.buisness.enums.ingredient.SortField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientFilterReq {
    private String query;
    @Min(0)
    @Max(3000)
    private Double minCalories = 0.0;
    @Min(0)
    @Max(3000)
    private Double maxCalories = 3000.0;
    @Min(0)
    @Max(3000)
    private Double minProteins = 0.0;
    @Min(0)
    @Max(3000)
    private Double maxProteins = 3000.0;
    @Min(0)
    @Max(3000)
    private Double minFats = 0.0;
    @Min(0)
    @Max(3000)
    private Double maxFats = 3000.0;
    @Min(0)
    @Max(3000)
    private Double minCarbohydrates = 0.0;
    @Min(0)
    @Max(3000)
    private Double maxCarbohydrates = 3000.0;

    private SortField sortField = SortField.NAME;

    private SortOrder sortOrder = SortOrder.ASC;

}
