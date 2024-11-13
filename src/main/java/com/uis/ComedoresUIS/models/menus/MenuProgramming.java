package com.uis.ComedoresUIS.models.menus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uis.ComedoresUIS.models.admins.Administrator;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_amin")
    private Administrator admin;
    @OneToOne
    @JoinColumn(name = "id_type")
    private TypeMeal typeMeal;
    @ManyToOne
    @JoinColumn(name = "id_meal")
    private Meal meal;


}
