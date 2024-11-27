package com.uis.ComedoresUIS.persistence.models.menus;

import com.uis.ComedoresUIS.persistence.models.menus.enums.FoodCategoryEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodCategoryEnum name;

}
