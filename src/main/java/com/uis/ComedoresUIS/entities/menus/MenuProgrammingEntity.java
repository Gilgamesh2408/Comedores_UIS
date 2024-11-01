package com.uis.ComedoresUIS.entities.menus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uis.ComedoresUIS.entities.admins.AdministratorEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuProgrammingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_amin")
    private AdministratorEntity admin;
    @OneToOne
    @JoinColumn(name = "id_type")
    private TypeMealEntity typeMeal;
    @ManyToOne
    @JoinColumn(name = "id_meal")
    private MealEntity meal;


}
