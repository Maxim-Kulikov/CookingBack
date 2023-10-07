package com.example.cooking.dto.searchFilter.ingredient;

import com.example.cooking.dto.sortenum.ingredient.IngredientSortField;
import com.example.cooking.dto.sortenum.ingredient.ProductKindSortField;
import com.example.cooking.dto.sortenum.ingredient.SortFieldAndOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductKindSearchFilter {
    private List<SortFieldAndOrder<ProductKindSortField>> filters;
}
