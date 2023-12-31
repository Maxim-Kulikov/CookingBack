package com.example.cooking.data.model.postgres.dish;

import com.example.cooking.data.model.postgres.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cooking_time", nullable = false)
    private Integer cookingTime;

    @Column(name = "id_recipe_info", nullable = false)
    private String idRecipeInfo;

    @Column(name = "calories", nullable = false)
    private Double calories;

    @Column(name = "proteins", nullable = false)
    private Double proteins;

    @Column(name = "fats", nullable = false)
    private Double fats;

    @Column(name = "carbohydrates", nullable = false)
    private Double carbohydrates;

    @ManyToOne
    @JoinColumn(name = "id_dish_name", referencedColumnName = "id")
    private DishName dishName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

}
