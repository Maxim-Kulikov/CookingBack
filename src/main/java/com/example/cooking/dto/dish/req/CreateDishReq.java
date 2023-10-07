package com.example.cooking.dto.dish.req;

import com.example.cooking.dto.ingredient.UsedIngredient;
import com.example.cooking.dto.photo.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDishReq {
    @NotNull
    @Min(1)
    @Max(30000)
    private Integer idUser;

    @NotNull
    @Min(1)
    @Max(30000)
    private Integer idDishName;

    @NotNull
    @Min(1)
    @Max(30000)
    private Integer cookingTime;

    @NotEmpty
    private List<UsedIngredient> usedIngredients;

    private Photo photo;
}
