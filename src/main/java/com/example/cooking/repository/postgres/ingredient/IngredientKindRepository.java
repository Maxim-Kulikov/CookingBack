package com.example.cooking.repository.postgres.ingredient;

import com.example.cooking.model.postgres.ingredient.IngredientKind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientKindRepository extends CrudRepository<IngredientKind, Integer> {
    Optional<IngredientKind> findByKindIgnoreCase(String kind);

    List<IngredientKind> findAllByKindContainsIgnoreCase(String kind);
}
