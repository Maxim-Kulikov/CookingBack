package com.example.cooking.presentation.dto.ingredient.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductKindResp {
    private Integer id;
    private String kind;
}
