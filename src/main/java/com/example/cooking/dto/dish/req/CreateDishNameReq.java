package com.example.cooking.dto.dish.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDishNameReq {
    @NotBlank
    @Size(min=2, max=45)
    private String name;

    @NotNull
    @Min(1)
    @Max(30000)
    private Integer idDishType;
}
