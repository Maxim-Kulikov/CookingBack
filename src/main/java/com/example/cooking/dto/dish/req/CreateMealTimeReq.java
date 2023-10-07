package com.example.cooking.dto.dish.req;

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
public class CreateMealTimeReq {
    @NotBlank
    @Size(min = 2, max = 45)
    private String dayPart;
}
