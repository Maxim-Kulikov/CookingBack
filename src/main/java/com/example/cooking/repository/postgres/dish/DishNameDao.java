package com.example.cooking.repository.postgres.dish;

import com.example.cooking.model.postgres.dish.DishName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishNameDao extends CrudRepository<DishName, Integer> {
}
