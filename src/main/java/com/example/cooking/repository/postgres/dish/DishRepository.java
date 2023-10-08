package com.example.cooking.repository.postgres.dish;

import com.example.cooking.model.postgres.dish.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer> {
    List<Dish> findAllByIdUser(int idUser);

    List<Dish> findAllByNameContainsIgnoreCase(String query);

    @Query(value = "select d from Dish d where d.name like %?1% " +
            "and d.calories between ?2 and ?3 " +
    "and d.proteins between ?4 and ?5 " +
    "and d.fats between ?6 and ?7 " +
    "and d.carbohydrates between ?8 and ?9 " +
    "and d.cookingTime between ?10 and ?11 " +
    "and d.idUser in ?12")
    List<Dish> findAllWithFilter(String query,
                                 Integer minCalories,
                                 Integer maxCalories,
                                 Integer minProteins,
                                 Integer maxProteins,
                                 Integer minFats,
                                 Integer maxFats,
                                 Integer minCarbohydrates,
                                 Integer maxCarbohydrates,
                                 Integer minCookingTime,
                                 Integer maxCookingTime,
                                 List<Integer> userIds);
}
