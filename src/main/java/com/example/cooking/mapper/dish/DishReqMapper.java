package com.example.cooking.mapper.dish;

import com.example.cooking.dto.dish.req.CreateDishNameReq;
import com.example.cooking.dto.dish.req.CreateDishReq;
import com.example.cooking.dto.dish.req.CreateDishTypeReq;
import com.example.cooking.dto.dish.req.CreateMealTimeReq;
import com.example.cooking.model.postgres.dish.Dish;
import com.example.cooking.model.postgres.dish.DishName;
import com.example.cooking.model.postgres.dish.DishType;
import com.example.cooking.model.postgres.dish.MealTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DishReqMapper {
    /*@Mapping(target = "id", ignore = true)
    Dish toDish(CreateDishReq req, DishName dishName, Integer idRecipeInfo, Integer idUser);
*/
    @Mapping(target = "id", ignore = true)
    DishName toDishName(CreateDishNameReq req, DishType dishType);

    @Mapping(target = "id", ignore = true)
    DishType toDishType(CreateDishTypeReq req, MealTime mealTime);

    @Mapping(target = "id", ignore = true)
    MealTime toMealTime(CreateMealTimeReq req);
}
