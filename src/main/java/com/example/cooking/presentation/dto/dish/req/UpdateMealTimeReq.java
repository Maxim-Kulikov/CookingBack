package com.example.cooking.presentation.dto.dish.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMealTimeReq {
    @Size(min = 2, max = 45)
    private String dayPart;
}
