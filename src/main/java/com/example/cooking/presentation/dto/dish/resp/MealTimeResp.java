package com.example.cooking.presentation.dto.dish.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealTimeResp {
    private Integer id;
    private String dayPart;
}
