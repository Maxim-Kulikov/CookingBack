package com.example.cooking.service.dish.impl;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.dish.req.CreateDishReq;
import com.example.cooking.dto.dish.req.UpdateDishReq;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.dto.ingredient.UsedIngredient;
import com.example.cooking.exception.dish.DishNotFoundException;
import com.example.cooking.mapper.dish.DishReqMapper;
import com.example.cooking.mapper.dish.DishRespMapper;
import com.example.cooking.model.mongo.RecipeInfo;
import com.example.cooking.model.postgres.dish.Dish;
import com.example.cooking.model.postgres.dish.DishName;
import com.example.cooking.model.postgres.ingredient.Ingredient;
import com.example.cooking.repository.mongo.RecipeInfoRepository;
import com.example.cooking.repository.postgres.dish.DishRepository;
import com.example.cooking.service.dish.DishService;
import com.example.cooking.service.ingredient.impl.IngredientServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private IngredientServiceImpl ingredientService;

    @Autowired
    private RecipeInfoRepository recipeInfoRepository;

    @Autowired
    private DishNameServiceImpl dishNameService;

    @Autowired
    private DishReqMapper reqMapper;

    @Autowired
    private DishRespMapper respMapper;

    @Override
    public DishResp save(CreateDishReq req) {
        DishName dishName = dishNameService.getDishNameOrThrowException(req.getIdDishName());
        Nutritional nutritional = new Nutritional();

        req.getUsedIngredients().forEach(usedIngredient -> changeNutritional(usedIngredient, nutritional));

        RecipeInfo recipeInfo = recipeInfoRepository.save(RecipeInfo.builder()
                .usedIngredients(req.getUsedIngredients())
                .photo(req.getPhoto())
                .build());

        Nutritional nutritionalIn100Grams = nutritional.getNutritionalIn100Grams();

        Dish dish = dishRepository.save(Dish.builder()
                .dishName(dishName)
                .idUser(req.getIdUser())
                .idRecipeInfo(recipeInfo.getId())
                .calories(nutritionalIn100Grams.calories)
                .proteins(nutritionalIn100Grams.proteins)
                .fats(nutritionalIn100Grams.fats)
                .carbohydrates(nutritionalIn100Grams.carbohydrates)
                .cookingTime(req.getCookingTime())
                .build());

        return respMapper.toDishResp(dish);
    }

    @Override
    public DishResp update(Integer id, UpdateDishReq req) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public DishResp findById(Integer id) {
        return null;
    }

    @Override
    public List<DishResp> findAllByQuery(Query query) {
        return null;
    }

    private Dish getDishOrThrowException(int id) {
        return dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
    }

    private List<Dish> getDishesByUserId(int userId) {
        return dishRepository.findAllByIdUser(userId);
    }

    private void changeNutritional(UsedIngredient usedIngredient, Nutritional nutritional) {
        Ingredient ingredient = ingredientService.getIngredientOrThrowException(usedIngredient.getIdIngredient());
        double weightIndex = (double) usedIngredient.getWeight() / 100;
        nutritional.addCalories(ingredient.getCalories() * weightIndex);
        nutritional.addProteins(ingredient.getProteins() * weightIndex);
        nutritional.addFats(ingredient.getFats() * weightIndex);
        nutritional.addCarbohydrates(ingredient.getCarbohydrates() * weightIndex);
        nutritional.addWeight(usedIngredient.getWeight());
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class Nutritional {
        private Double calories = 0.0;
        private Double proteins = 0.0;
        private Double fats = 0.0;
        private Double carbohydrates = 0.0;
        private Integer weight = 0;

        void addCalories(Double calories) {
            this.calories += calories;
        }

        void addProteins(Double proteins) {
            this.proteins += proteins;
        }

        void addFats(Double fats) {
            this.fats += fats;
        }

        void addCarbohydrates(Double carbohydrates) {
            this.carbohydrates += carbohydrates;
        }

        void addWeight(int weight) {
            this.weight += weight;
        }

        Nutritional getNutritionalIn100Grams() {
            double weightIndex = (double) weight / 100;
            return Nutritional.builder()
                    .calories(calories / weightIndex)
                    .proteins(proteins / weightIndex)
                    .fats(fats / weightIndex)
                    .carbohydrates(carbohydrates / weightIndex)
                    .weight(weight)
                    .build();
        }
    }

}
