package com.example.cooking.repository.postgres.dish;

import com.example.cooking.model.postgres.dish.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer> {
    List<Dish> findAllByIdUser(int idUser);
}
