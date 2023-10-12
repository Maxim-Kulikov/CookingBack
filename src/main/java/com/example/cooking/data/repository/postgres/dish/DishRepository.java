package com.example.cooking.data.repository.postgres.dish;

import com.example.cooking.data.model.postgres.dish.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer> {
    List<Dish> findAllByIdUser(int idUser);

    List<Dish> findAllByNameContainsIgnoreCase(String query);

    @Query(value = "select d from Dish d where (:query is null or upper(d.name) like upper(concat('%', :query, '%'))) " +
            "and d.calories between :minCalories and :maxCalories " +
            "and d.proteins between :minProteins and :maxProteins " +
            "and d.fats between :minFats and :maxFats " +
            "and d.carbohydrates between :minCarbohydrates and :maxCarbohydrates " +
            "and d.cookingTime between :minCookingTime and :maxCookingTime " +
            "and (:userIds is null or d.idUser in :userIds)")
    List<Dish> findAllWithFilter(@Param("query") String query,
                                 @Param("minCalories") Double minCalories,
                                 @Param("maxCalories") Double maxCalories,
                                 @Param("minProteins") Double minProteins,
                                 @Param("maxProteins") Double maxProteins,
                                 @Param("minFats") Double minFats,
                                 @Param("maxFats") Double maxFats,
                                 @Param("minCarbohydrates") Double minCarbohydrates,
                                 @Param("maxCarbohydrates") Double maxCarbohydrates,
                                 @Param("minCookingTime") Integer minCookingTime,
                                 @Param("maxCookingTime") Integer maxCookingTime,
                                 @Param("userIds") List<Integer> userIds);
}
