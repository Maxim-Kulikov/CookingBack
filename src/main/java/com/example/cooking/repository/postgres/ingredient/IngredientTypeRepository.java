package com.example.cooking.repository.postgres.ingredient;

import com.example.cooking.model.postgres.ingredient.IngredientType;
import com.example.cooking.model.postgres.ingredient.ProductKind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientTypeRepository extends CrudRepository<IngredientType, Integer> {
    boolean existsByType(String type);

    Optional<IngredientType> findByType(String type);

    List<IngredientType> findAllByTypeContainsIgnoreCase(String type);
}
