package com.uis.ComedoresUIS.persistence.repositories.menus;

import com.uis.ComedoresUIS.persistence.models.menus.FoodCategory;
import com.uis.ComedoresUIS.persistence.models.menus.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> getAllIngredientByFoodCategory(FoodCategory foodCategory);

    Ingredient findByName(String name);
}
