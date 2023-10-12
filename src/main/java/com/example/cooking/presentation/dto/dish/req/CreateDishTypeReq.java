package com.example.cooking.presentation.dto.dish.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDishTypeReq {
    @NotNull
    @Min(1)
    @Max(30000)
    private Integer idMealTime;

    @NotBlank
    @Size(min = 2, max = 45)
    private String type;
}
