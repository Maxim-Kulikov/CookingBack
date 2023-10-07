package com.example.cooking.dto.searchFilter;

import com.example.cooking.dto.sortenum.EnergyValueField;
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
public class EnergyValueSearchFilter {
    List<SortFieldAndOrder<EnergyValueField>> energyValueFilters;
}
