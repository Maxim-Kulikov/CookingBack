package com.example.cooking.mapper.dish;

import com.example.cooking.dto.dish.resp.DishNameResp;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.dto.dish.resp.DishTypeResp;
import com.example.cooking.dto.dish.resp.MealTimeResp;
import com.example.cooking.dto.ingredient.resp.UsedIngredientResp;
import com.example.cooking.model.postgres.dish.Dish;
import com.example.cooking.model.postgres.dish.DishName;
import com.example.cooking.model.postgres.dish.DishType;
import com.example.cooking.model.postgres.dish.MealTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishRespMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "idDishType", source = "dishType.id")
    @Mapping(target = "dishType", source = "dishType.type")
    @Mapping(target = "idMealTime", source = "dishType.mealTime.id")
    @Mapping(target = "dayPart", source = "dishType.mealTime.dayPart")
    DishNameResp toDishNameResp(DishName dishName);

    @Mapping(target = "id", source = "dish.id")
    @Mapping(target = "idRecipeInfo", source = "dish.idRecipeInfo")
    @Mapping(target = "idUser", source = "dish.idUser")
    @Mapping(target = "idDishType", source = "dish.dishName.dishType.id")
    @Mapping(target = "dishType", source = "dish.dishName.dishType.type")
    @Mapping(target = "idDishName", source = "dish.dishName.id")
    @Mapping(target = "dishName", source = "dish.dishName.name")
    @Mapping(target = "idMealTime", source = "dish.dishName.dishType.mealTime.id")
    @Mapping(target = "dayPart", source = "dish.dishName.dishType.mealTime.dayPart")
    DishResp toDishResp(Dish dish, List<UsedIngredientResp> usedIngredients);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "idMealTime", source = "mealTime.id")
    @Mapping(target = "dayPart", source = "mealTime.dayPart")
    DishTypeResp toDishTypeResp(DishType dishType);

    MealTimeResp toMealTimeResp(MealTime mealTime);

/*    List<DishNameResp> toDishNameResps(List<DishName> dishNames);
    List<DishResp> toDishResps(List<Dish> dishes);
    List<DishTypeResp> toDishTypeResps(List<DishType> dishTypes);
    List<MealTimeResp> toMealTimeResps(List<MealTime> mealTimes);*/
}
