package com.uis.ComedoresUIS.persistence.repositories.menus;

import com.uis.ComedoresUIS.persistence.models.menus.MealIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealIngredientRepository extends JpaRepository<MealIngredient, Long> {
}
