package com.example.cooking.dto.dish.req;

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
public class UpdateDishNameReq {
    @Size(min=2, max=45)
    private String name;

    @Min(1)
    @Max(30000)
    private Integer idDishType;
}
