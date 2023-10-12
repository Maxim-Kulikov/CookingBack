package com.example.cooking.presentation.dto.dish.req;

import com.example.cooking.presentation.dto.ingredient.UsedIngredient;
import com.example.cooking.presentation.dto.photo.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDishReq {
    @NotBlank
    private String name;

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
