package com.example.cooking.service.dish.impl;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.dish.req.CreateDishReq;
import com.example.cooking.dto.dish.req.UpdateDishReq;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.dto.ingredient.UsedIngredient;
import com.example.cooking.exception.dish.DishNotFoundException;
import com.example.cooking.exception.dish.RecipeInfoNotFoundException;
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
import com.example.cooking.util.DataValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    @Transactional
    @Override
    public DishResp save(CreateDishReq req) {
        DishName dishName = dishNameService.getDishNameOrThrowException(req.getIdDishName());
        Nutritional nutritional = getNutritional(req.getUsedIngredients());

        RecipeInfo recipeInfo = recipeInfoRepository.save(RecipeInfo.builder()
                .usedIngredients(req.getUsedIngredients())
                .photo(req.getPhoto())
                .build());

        Dish dish = dishRepository.save(Dish.builder()
                .name(req.getName())
                .dishName(dishName)
                .idUser(req.getIdUser())
                .idRecipeInfo(recipeInfo.getId())
                .cookingTime(req.getCookingTime())
                .build());

        setNutritionalIn100GramsToDish(dish, nutritional);

        return respMapper.toDishResp(dish);
    }

    @Transactional
    @Override
    public DishResp update(Integer id, UpdateDishReq req) {
        Dish dish = getDishOrThrowException(id);
        RecipeInfo recipeInfo = getRecipeInfoOrThrowException(dish.getIdRecipeInfo());

        if (req.getIdDishName() != null && !Objects.equals(dish.getDishName().getId(), req.getIdDishName())) {
            dish.setDishName(dishNameService.getDishNameOrThrowException(req.getIdDishName()));
        }

        if(req.getUsedIngredients() != null && !req.getUsedIngredients().isEmpty()) {
            Nutritional nutritional = getNutritional(req.getUsedIngredients());
            setNutritionalIn100GramsToDish(dish, nutritional);
            recipeInfo.setUsedIngredients(req.getUsedIngredients());
        }

        if (!DataValidator.isNullOrEmpty(req.getName())) {
            dish.setName(req.getName());
        }

        if (req.getCookingTime() != null) {
            dish.setCookingTime(req.getCookingTime());
        }

        if(req.getIdUser() != null) {
            dish.setIdUser(req.getIdUser());
        }

        dishRepository.save(dish);
        recipeInfoRepository.save(recipeInfo);

        return respMapper.toDishResp(dish);
    }

    @Transactional
    @Override
    public void delete(Integer id) {

    }

    @Transactional
    @Override
    public DishResp findById(Integer id) {
        return null;
    }

    @Transactional
    @Override
    public List<DishResp> findAllByQuery(Query query) {
        return null;
    }

    private Nutritional getNutritional(List<UsedIngredient> usedIngredients) {
        Nutritional nutritional = new Nutritional();
        usedIngredients.forEach(usedIngredient -> changeNutritional(usedIngredient, nutritional));
        return nutritional;
    }

    private Dish getDishOrThrowException(int id) {
        return dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
    }

    private RecipeInfo getRecipeInfoOrThrowException(String id) {
        return recipeInfoRepository.findById(id).orElseThrow(() -> new RecipeInfoNotFoundException(id));
    }

    private List<Dish> getDishesByUserId(int userId) {
        return dishRepository.findAllByIdUser(userId);
    }

    private void setNutritionalIn100GramsToDish(Dish dish, Nutritional nutritional) {
        Nutritional nutritionalIn100Grams = nutritional.getNutritionalIn100Grams();
        dish.setCalories(nutritionalIn100Grams.calories);
        dish.setProteins(nutritionalIn100Grams.proteins);
        dish.setFats(nutritional.fats);
        dish.setCarbohydrates(nutritional.carbohydrates);
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
