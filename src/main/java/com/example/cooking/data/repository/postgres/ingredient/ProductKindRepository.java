package com.example.cooking.data.repository.postgres.ingredient;

import com.example.cooking.data.model.postgres.ingredient.ProductKind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductKindRepository extends CrudRepository<ProductKind, Integer> {
    Optional<ProductKind> findByNameIgnoreCase(String name);

    List<ProductKind> findAllByNameContainsIgnoreCase(String name);

}
