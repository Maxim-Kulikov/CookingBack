package com.example.cooking.presentation.dto.dish.req;

import com.example.cooking.presentation.dto.ingredient.UsedIngredient;
import com.example.cooking.presentation.dto.photo.Photo;
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

    private Photo photo;

    private List<UsedIngredient> usedIngredients;
}
