package com.uis.ComedoresUIS.models.menus;

import com.uis.ComedoresUIS.models.menus.enums.TypeMealEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeMealEnum name;

}
