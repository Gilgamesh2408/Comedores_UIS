package com.uis.ComedoresUIS.models.menus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {

    private String name;
    private Integer quantity;

}