package com.uis.ComedoresUIS.models.menus;

import com.uis.ComedoresUIS.models.menus.enums.FoodCategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
