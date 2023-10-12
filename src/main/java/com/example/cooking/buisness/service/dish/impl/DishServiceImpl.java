package com.example.cooking.buisness.service.dish.impl;

import com.example.cooking.buisness.enums.SortOrder;
import com.example.cooking.buisness.service.dish.DishService;
import com.example.cooking.buisness.service.ingredient.impl.IngredientServiceImpl;
import com.example.cooking.data.model.mongo.RecipeInfo;
import com.example.cooking.data.model.postgres.dish.Dish;
import com.example.cooking.data.model.postgres.dish.DishName;
import com.example.cooking.data.model.postgres.ingredient.Ingredient;
import com.example.cooking.data.repository.mongo.RecipeInfoRepository;
import com.example.cooking.data.repository.postgres.dish.DishRepository;
import com.example.cooking.exception.dish.DishNotFoundException;
import com.example.cooking.exception.dish.RecipeInfoNotFoundException;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.dish.req.CreateDishReq;
import com.example.cooking.presentation.dto.dish.req.DishFilterReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishReq;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import com.example.cooking.presentation.dto.ingredient.UsedIngredient;
import com.example.cooking.presentation.dto.mapper.dish.DishRespMapper;
import com.example.cooking.util.DataValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        Dish dish = Dish.builder()
                .name(req.getName())
                .dishName(dishName)
                .idUser(req.getIdUser())
                .idRecipeInfo(recipeInfo.getId())
                .cookingTime(req.getCookingTime())
                .build();
        setNutritionalIn100GramsToDish(dish, nutritional);

        dishRepository.save(dish);
        return respMapper.toDishResp(dish, recipeInfo);
    }

    @Transactional
    @Override
    public DishResp update(Integer id, UpdateDishReq req) {
        Dish dish = getDishOrThrowException(id);
        RecipeInfo recipeInfo = getRecipeInfoOrThrowException(dish.getIdRecipeInfo());

        if (req.getIdDishName() != null && !Objects.equals(dish.getDishName().getId(), req.getIdDishName())) {
            dish.setDishName(dishNameService.getDishNameOrThrowException(req.getIdDishName()));
        }

        if (req.getUsedIngredients() != null && !req.getUsedIngredients().isEmpty()) {
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

        if (req.getIdUser() != null) {
            dish.setIdUser(req.getIdUser());
        }

        dishRepository.save(dish);
        recipeInfoRepository.save(recipeInfo);

        return respMapper.toDishResp(dish, recipeInfo);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        String recipeInfoId = getDishOrThrowException(id).getIdRecipeInfo();
        dishRepository.deleteById(id);
        recipeInfoRepository.deleteById(recipeInfoId);
    }

    @Transactional
    @Override
    public DishResp findById(Integer id) {
        Dish dish = getDishOrThrowException(id);
        RecipeInfo recipeInfo = getRecipeInfoOrThrowException(dish.getIdRecipeInfo());
        return respMapper.toDishResp(dish, recipeInfo);
    }

    @Transactional
    @Override
    public List<DishResp> findAllByQuery(Query query) {
        return (DataValidator.isObjectOrFieldNull(query) ? (List<Dish>) dishRepository.findAll()
                : dishRepository.findAllByNameContainsIgnoreCase(query.getQuery()))
                .stream()
                .map(dish ->
                        respMapper.toDishResp(dish,
                                recipeInfoRepository.findById(dish.getIdRecipeInfo()).orElseGet(RecipeInfo::new)))
                .collect(Collectors.toList());
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

    @Override
    public List<Dish> getDishesByUserId(int userId) {
        return dishRepository.findAllByIdUser(userId);
    }

    private void setNutritionalIn100GramsToDish(Dish dish, Nutritional nutritional) {
        Nutritional nutritionalIn100Grams = nutritional.getNutritionalIn100Grams();
        dish.setCalories(nutritionalIn100Grams.getCalories());
        dish.setProteins(nutritionalIn100Grams.getProteins());
        dish.setFats(nutritionalIn100Grams.getFats());
        dish.setCarbohydrates(nutritionalIn100Grams.getCarbohydrates());
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

    @Transactional
    @Override
    public List<DishResp> findAllByFilter(DishFilterReq req) {
        if(req == null) {
            return ((List<Dish>) dishRepository.findAll())
                    .stream()
                    .map(dish -> respMapper.toDishResp(dish, recipeInfoRepository.findById(dish.getIdRecipeInfo())
                    .orElseGet(RecipeInfo::new)))
                    .collect(Collectors.toList());
        }
        return dishRepository.findAllWithFilter(
                        req.getQuery(),
                        req.getMinCalories(), req.getMaxCalories(), req.getMinProteins(), req.getMaxProteins(),
                        req.getMinFats(), req.getMaxFats(), req.getMinCarbohydrates(), req.getMaxCarbohydrates(),
                        req.getMinCookingTime(), req.getMaxCookingTime(),
                        req.getUserIds()
                )
                .stream()
                .sorted((o1, o2) -> {
                    int result = 0;
                    switch (req.getSortField()) {
                        case CALORIES -> result = o1.getCalories().compareTo(o2.getCalories());
                        case PROTEINS -> result = o1.getProteins().compareTo(o2.getProteins());
                        case FATS -> result = o1.getFats().compareTo(o2.getFats());
                        case CARBOHYDRATES -> result = o1.getCarbohydrates().compareTo(o2.getCarbohydrates());
                        case COOKING_TIME -> result = o1.getCookingTime().compareTo(o2.getCookingTime());
                        default -> result = o1.getName().compareTo(o2.getName());
                    }
                    return req.getSortOrder() == SortOrder.DESC ? result * (-1) : result;
                })
                .map(dish -> respMapper.toDishResp(dish, recipeInfoRepository.findById(dish.getIdRecipeInfo())
                        .orElseGet(RecipeInfo::new)))
                .collect(Collectors.toList());
    }

    @Builder
    @AllArgsConstructor
    @Getter
    private static class Nutritional {
        private Double calories;
        private Double proteins;
        private Double fats;
        private Double carbohydrates;
        private Integer weight;

        Nutritional() {
            calories = 0.0;
            proteins = 0.0;
            fats = 0.0;
            carbohydrates = 0.0;
            weight = 0;
        }

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
