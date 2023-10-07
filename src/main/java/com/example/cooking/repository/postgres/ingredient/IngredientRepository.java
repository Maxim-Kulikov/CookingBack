package com.example.cooking.repository.postgres.ingredient;

import com.example.cooking.model.postgres.ingredient.Ingredient;
import com.example.cooking.model.postgres.ingredient.ProductKind;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    Optional<Ingredient> findByIngredientName(String ingredientName);

    List<Ingredient> findAllByIngredientNameContainsIgnoreCase(String name);
}
