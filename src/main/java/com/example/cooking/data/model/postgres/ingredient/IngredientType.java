package com.example.cooking.data.model.postgres.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ingredient_type")
public class IngredientType {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_ingredient_kind", referencedColumnName = "id")
    private IngredientKind ingredientKind;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingredient_type")
    private List<Ingredient> ingredients;
}
