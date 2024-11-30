package com.uis.ComedoresUIS.persistence.models.menus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_meal")
    @JsonIgnore
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    private Integer quantity;

}
