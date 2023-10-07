package com.example.cooking.model.postgres.dish;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dish_name")
public class DishName {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_dish_type", referencedColumnName = "id")
    private DishType dishType;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Dish> dishes;

}
