package com.example.cooking.dto.dish.req;

import com.example.cooking.dto.sortenum.ingredient.SortFieldAndOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishFilterReq {
    private String query;
    @Min(0)
    @Max(3000)
    private Integer minCalories = 0;
    @Min(0)
    @Max(3000)
    private Integer maxCalories = 3000;
    @Min(0)
    @Max(3000)
    private Integer minProteins = 0;
    @Min(0)
    @Max(3000)
    private Integer maxProteins = 3000;
    @Min(0)
    @Max(3000)
    private Integer minFats = 0;
    @Min(0)
    @Max(3000)
    private Integer maxFats = 3000;
    @Min(0)
    @Max(3000)
    private Integer minCarbohydrates = 0;
    @Min(0)
    @Max(3000)
    private Integer maxCarbohydrates = 3000;
    @Min(0)
    @Max(3000)
    private Integer minCookingTime = 0;
    @Min(0)
    @Max(3000)
    private Integer maxCookingTime = 3000;

    private List<Integer> userIds;

    private SortField sortField = SortField.NAME;

    private SortOrder sortOrder = SortOrder.ASC;
}
