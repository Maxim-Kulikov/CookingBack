package com.example.cooking.repository.postgres.dish;

import com.example.cooking.model.postgres.dish.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishDao extends CrudRepository<Dish, Integer> {
}
