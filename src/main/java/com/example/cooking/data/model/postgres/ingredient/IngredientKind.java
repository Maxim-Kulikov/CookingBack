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
@Table(name = "ingredient_kind")
public class IngredientKind {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kind", nullable = false, unique = true)
    private String kind;

    @ManyToOne
    @JoinColumn(name = "id_product_kind", referencedColumnName = "id")
    private ProductKind productKind;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingredient_kind")
    private List<IngredientType> ingredientTypes;
}
