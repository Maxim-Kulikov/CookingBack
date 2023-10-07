package com.example.cooking.dto.dish.req;

import com.example.cooking.dto.ingredient.UsedIngredient;
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
public class UpdateDishReq {
    @Min(1)
    @Max(30000)
    private Integer idUser;
    
    private String name;

    @Min(1)
    @Max(30000)
    private Integer idDishName;

    @Min(1)
    @Max(30000)
    private Integer cookingTime;

    private List<UsedIngredient> usedIngredients;
}
