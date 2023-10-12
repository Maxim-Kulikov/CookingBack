package com.example.cooking.data.repository.postgres.ingredient;

import com.example.cooking.data.model.postgres.ingredient.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    Optional<Ingredient> findByIngredientNameIgnoreCase(String ingredientName);

    List<Ingredient> findAllByIngredientNameContainsIgnoreCase(String name);

    @Query(value = "select i from Ingredient i where (:query is null or upper(i.ingredientName) like upper(concat('%', :query, '%'))) " +
            "and i.calories between :minCalories and :maxCalories " +
            "and i.proteins between :minProteins and :maxProteins " +
            "and i.fats between :minFats and :maxFats " +
            "and i.carbohydrates between :minCarbohydrates and :maxCarbohydrates ")
    List<Ingredient> findAllWithFilter(@Param("query") String query,
                                 @Param("minCalories") Double minCalories,
                                 @Param("maxCalories") Double maxCalories,
                                 @Param("minProteins") Double minProteins,
                                 @Param("maxProteins") Double maxProteins,
                                 @Param("minFats") Double minFats,
                                 @Param("maxFats") Double maxFats,
                                 @Param("minCarbohydrates") Double minCarbohydrates,
                                 @Param("maxCarbohydrates") Double maxCarbohydrates);
}
