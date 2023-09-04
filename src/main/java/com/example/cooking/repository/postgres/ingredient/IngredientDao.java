package com.example.cooking.repository.postgres.ingredient;

import com.example.cooking.model.postgres.ingredient.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientDao extends CrudRepository<Ingredient, Integer> {
    Optional<Ingredient> findByIngredientName(String ingredientName);
}
