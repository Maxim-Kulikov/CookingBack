package com.example.cooking.dto.dish.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishTypeResp {
    private Integer id;
    private String type;

    private Integer idMealTime;
    private String dayPart;
}
