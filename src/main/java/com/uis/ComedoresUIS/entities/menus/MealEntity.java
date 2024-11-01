package com.uis.ComedoresUIS.entities.menus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String mainCourse;
    private String soup;
    private String drink;
    private String dessert;

}
