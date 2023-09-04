package com.example.cooking.repository.postgres.dish;

import com.example.cooking.model.postgres.dish.DishType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishTypeDao extends CrudRepository<DishType, Integer> {
}
