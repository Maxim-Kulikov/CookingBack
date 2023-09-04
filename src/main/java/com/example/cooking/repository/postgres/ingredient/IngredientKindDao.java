package com.example.cooking.repository.postgres.ingredient;

import com.example.cooking.model.postgres.ingredient.IngredientKind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientKindDao extends CrudRepository<IngredientKind, Integer> {
    boolean existsByKind(String kind);

    Optional<IngredientKind> findByKind(String kind);
}
