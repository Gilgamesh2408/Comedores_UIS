package com.uis.ComedoresUIS.persistence.models.menus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uis.ComedoresUIS.persistence.models.admins.Administrator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuProgramming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_amin")
    private Administrator admin;
    @ManyToOne
    @JoinColumn(name = "id_type", unique = false)
    private TypeMeal typeMeal;
    @ManyToOne
    @JoinColumn(name = "id_meal")
    private Meal meal;


}
