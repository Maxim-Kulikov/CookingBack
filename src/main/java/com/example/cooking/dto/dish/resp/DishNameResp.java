package com.example.cooking.dto.dish.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishNameResp {
    private Integer id;

    private String name;

    private Integer idDishType;

    private String dishType;

    private Integer idMealTime;

    private String dayPart;
}
