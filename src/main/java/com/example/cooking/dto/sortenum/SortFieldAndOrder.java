package com.example.cooking.dto.sortenum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortFieldAndOrder {
    private String sortField;
    private String sortOrder;
    private Integer minValue;
    private Integer maxValue;
}
