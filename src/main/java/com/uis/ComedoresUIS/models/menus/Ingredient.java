package com.uis.ComedoresUIS.models.menus;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;
    private Float calories;
    private Float proteins;
    private Float carbohydrates;
    private Float fats;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private FoodCategory foodCategory;

}
