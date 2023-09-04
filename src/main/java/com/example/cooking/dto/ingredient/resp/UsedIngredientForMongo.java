package com.example.cooking.dto.ingredient.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsedIngredientForMongo {
    private Integer idIngredient;
    private Integer weight;
}
