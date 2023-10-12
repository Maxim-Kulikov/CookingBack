package com.example.cooking.data.repository.postgres.ingredient;

import com.example.cooking.data.model.postgres.ingredient.IngredientType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientTypeRepository extends CrudRepository<IngredientType, Integer> {
    Optional<IngredientType> findByTypeIgnoreCase(String type);

    List<IngredientType> findAllByTypeContainsIgnoreCase(String type);
}
