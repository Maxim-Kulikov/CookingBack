package com.example.cooking.repository.postgres.ingredient;

import com.example.cooking.model.postgres.ingredient.ProductKind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductKindDao extends CrudRepository<ProductKind, Integer> {
    boolean existsByName(String name);

    Optional<ProductKind> findByName(String name);
}
