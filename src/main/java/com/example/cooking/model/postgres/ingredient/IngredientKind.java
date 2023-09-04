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
}
