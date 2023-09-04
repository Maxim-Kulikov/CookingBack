package com.example.cooking.model.postgres.ingredient;

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
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_ingredient_type", referencedColumnName = "id")
    private IngredientType ingredientType;

    @Column(name = "calories", nullable = false)
    private Integer calories;

    @Column(name = "proteins", nullable = false)
    private Integer proteins;

    @Column(name = "fats", nullable = false)
    private Integer fats;

    @Column(name = "carbohydrates", nullable = false)
    private Integer carbohydrates;

    @Column(name = "ingredient_name", nullable = false)
    private String ingredientName;
}
