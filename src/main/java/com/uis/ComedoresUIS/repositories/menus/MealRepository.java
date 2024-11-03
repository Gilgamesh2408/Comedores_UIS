package com.uis.ComedoresUIS.repositories.menus;

import com.uis.ComedoresUIS.models.menus.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
