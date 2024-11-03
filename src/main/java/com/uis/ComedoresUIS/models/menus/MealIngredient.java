package com.uis.ComedoresUIS.models.menus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_meal")
    private Meal meal;
    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

}
