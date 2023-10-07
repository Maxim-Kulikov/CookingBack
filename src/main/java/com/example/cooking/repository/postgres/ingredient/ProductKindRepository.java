package com.example.cooking.repository.postgres.ingredient;

import com.example.cooking.model.postgres.ingredient.ProductKind;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface ProductKindRepository extends CrudRepository<ProductKind, Integer> {
    boolean existsByName(String name);

    Optional<ProductKind> findByName(String name);

    List<ProductKind> findAllByNameContainsIgnoreCase(String name);

}
