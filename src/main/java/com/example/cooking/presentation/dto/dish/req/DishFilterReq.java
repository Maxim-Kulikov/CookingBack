package com.example.cooking.presentation.dto.dish.req;

import com.example.cooking.buisness.enums.common.SortOrder;
import com.example.cooking.buisness.enums.dish.SortField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishFilterReq {
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
