package com.uis.ComedoresUIS.models.menus.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuProgrammingDTO {

    private LocalDate date;
    private Long admin;
    private Long typeMeal;
    private Long meal;

}
