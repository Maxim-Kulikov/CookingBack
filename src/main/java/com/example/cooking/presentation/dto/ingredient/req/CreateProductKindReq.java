package com.example.cooking.presentation.dto.ingredient.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductKindReq {
    @NotBlank
    @Size(min = 2, max = 45)
    private String name;
}
