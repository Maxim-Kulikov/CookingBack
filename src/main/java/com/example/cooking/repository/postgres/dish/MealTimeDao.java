package com.example.cooking.repository.postgres.dish;

import com.example.cooking.model.postgres.dish.MealTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTimeDao extends CrudRepository<MealTime, Integer> {
}
