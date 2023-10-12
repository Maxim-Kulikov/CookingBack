package com.example.cooking.data.model.postgres.dish;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dish_type")
public class DishType {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", unique = true)
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_meal_time", referencedColumnName = "id")
    private MealTime mealTime;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<DishName> dishNames;
}
