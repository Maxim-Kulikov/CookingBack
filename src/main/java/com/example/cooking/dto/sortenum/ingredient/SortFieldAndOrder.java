package com.example.cooking.dto.sortenum.ingredient;

import com.example.cooking.dto.sortenum.SortOrder;
import com.example.cooking.dto.sortenum.ingredient.IngredientSortField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortFieldAndOrder<T> {
    private T sortField;
    private SortOrder sortOrder;
    private Integer minValue;
    private Integer maxValue;
}
