package com.uis.ComedoresUIS.models.menus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {

    private String mainCourse;
    private String soup;
    private String drink;
    private String dessert;
    private LocalDate createdAt;
    private List<IngredientDTO> ingredientsDTO;

}
