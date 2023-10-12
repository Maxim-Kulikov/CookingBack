package com.example.cooking.presentation.dto.dish.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDishTypeReq {
    @Min(1)
    @Max(30000)
    private Integer idMealTime;

    @Size(min = 2, max = 45)
    private String type;
}
