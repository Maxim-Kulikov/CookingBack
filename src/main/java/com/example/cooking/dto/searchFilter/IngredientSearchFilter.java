package com.example.cooking.dto.searchFilter;

import com.example.cooking.dto.sortenum.SortFieldAndOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientSearchFilter {
    private List<Integer> ids;
    private List<SortFieldAndOrder> sortFieldsAndOrders;
}
