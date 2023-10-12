package com.example.cooking.presentation.dto.mapper.dish;

import com.example.cooking.presentation.dto.dish.resp.DishNameResp;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import com.example.cooking.presentation.dto.dish.resp.DishTypeResp;
import com.example.cooking.presentation.dto.dish.resp.MealTimeResp;
import com.example.cooking.data.model.mongo.RecipeInfo;
import com.example.cooking.data.model.postgres.dish.Dish;
import com.example.cooking.data.model.postgres.dish.DishName;
import com.example.cooking.data.model.postgres.dish.DishType;
import com.example.cooking.data.model.postgres.dish.MealTime;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DishRespMapper {

    public DishNameResp toDishNameResp(DishName dishName) {
        return DishNameResp.builder()
                .id(dishName.getId())
                .name(dishName.getName())
                .idDishType(dishName.getDishType().getId())
                .dishType(dishName.getDishType().getType())
                .idMealTime(dishName.getDishType().getMealTime().getId())
                .dayPart(dishName.getDishType().getMealTime().getDayPart())
                .build();
    }

    public DishResp toDishResp(Dish dish, RecipeInfo recipeInfo) {
        return DishResp.builder()
                .id(dish.getId())
                .name(dish.getName())
                .cookingTime(dish.getCookingTime())
                .idUser(dish.getIdUser())
                .idDishType(dish.getDishName().getDishType().getId())
                .dishType(dish.getDishName().getDishType().getType())
                .idDishName(dish.getDishName().getId())
                .dishName(dish.getDishName().getName())
                .idMealTime(dish.getDishName().getDishType().getMealTime().getId())
                .dayPart(dish.getDishName().getDishType().getMealTime().getDayPart())
                .calories(dish.getCalories())
                .proteins(dish.getProteins())
                .fats(dish.getFats())
                .carbohydrates(dish.getCarbohydrates())
                .photo(recipeInfo.getPhoto())
                .usedIngredients(recipeInfo.getUsedIngredients())
                .build();
    }

    public DishTypeResp toDishTypeResp(DishType dishType) {
        return DishTypeResp.builder()
                .id(dishType.getId())
                .type(dishType.getType())
                .idMealTime(dishType.getMealTime().getId())
                .dayPart(dishType.getMealTime().getDayPart())
                .build();
    }

    public MealTimeResp toMealTimeResp(MealTime mealTime) {
        return MealTimeResp.builder()
                .id(mealTime.getId())
                .dayPart(mealTime.getDayPart())
                .build();
    }

    public List<DishNameResp> toDishNameResps(List<DishName> dishNames) {
        return dishNames.stream().map(this::toDishNameResp).collect(Collectors.toList());
    }

    public List<DishTypeResp> toDishTypeResps(List<DishType> dishTypes) {
        return dishTypes.stream().map(this::toDishTypeResp).collect(Collectors.toList());
    }

    public List<MealTimeResp> toMealTimeResps(List<MealTime> mealTimes) {
        return mealTimes.stream().map(this::toMealTimeResp).collect(Collectors.toList());
    }

}
