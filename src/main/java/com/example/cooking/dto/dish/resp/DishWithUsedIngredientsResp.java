package com.example.cooking.dto.dish.resp;

import com.example.cooking.dto.ingredient.resp.UsedIngredientResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishWithUsedIngredientsResp {
    private DishResp dishResp;
    private List<UsedIngredientResp> usedIngredients;
}
