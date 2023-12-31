package com.example.cooking.data.repository.postgres.dish;

import com.example.cooking.data.model.postgres.dish.MealTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealTimeRepository extends CrudRepository<MealTime, Integer> {
    Optional<MealTime> findByDayPartIgnoreCase(String dayPart);

    List<MealTime> findAllByDayPartContainsIgnoreCase(String dayPart);

    boolean existsByDayPartIgnoreCase(String dayPart);
}

