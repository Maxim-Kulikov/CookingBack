package com.example.cooking.data.repository.postgres.dish;

import com.example.cooking.data.model.postgres.dish.DishName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishNameRepository extends CrudRepository<DishName, Integer> {
    List<DishName> findAllByNameContainsIgnoreCase(String name);

    Optional<DishName> findFirstByNameIgnoreCaseAndDishTypeId(String name, Integer dishTypeId);

    boolean existsByNameIgnoreCase(String name);
}
