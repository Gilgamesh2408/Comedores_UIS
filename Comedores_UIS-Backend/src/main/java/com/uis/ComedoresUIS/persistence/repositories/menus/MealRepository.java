package com.uis.ComedoresUIS.persistence.repositories.menus;

import com.uis.ComedoresUIS.persistence.models.menus.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
