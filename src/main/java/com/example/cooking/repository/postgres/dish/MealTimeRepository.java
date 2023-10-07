package com.example.cooking.repository.postgres.dish;

import com.example.cooking.model.postgres.dish.MealTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealTimeRepository extends CrudRepository<MealTime, Integer> {
    Optional<MealTime> findByDayPart(String dayPart);

    List<MealTime> findAllByDayPartContainsIgnoreCase(String dayPart);
}

