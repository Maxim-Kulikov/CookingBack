package com.example.cooking.model.postgres.dish;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "meal_time")
public class MealTime {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "day_part", nullable = false, unique = true)
    private String dayPart;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<DishType> dishTypes;

}