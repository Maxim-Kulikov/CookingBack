package com.example.cooking.data.repository.postgres.dish;

import com.example.cooking.data.model.postgres.dish.DishType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishTypeRepository extends CrudRepository<DishType, Integer> {
    List<DishType> findAllByTypeContainsIgnoreCase(String type);

    Optional<DishType> findFirstByTypeIgnoreCaseAndMealTimeId(String type, Integer mealTimeId);

    boolean existsByTypeIgnoreCase(String type);
}
