package com.uis.ComedoresUIS.entities.menus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_meal")
    private MealEntity meal;
    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private IngredientEntity ingredient;

}
