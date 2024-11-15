package com.uis.ComedoresUIS.models.menus;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String mainCourse;
    private String soup;
    private String drink;
    private String dessert;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MealIngredient> ingredients = new ArrayList<>();
}
