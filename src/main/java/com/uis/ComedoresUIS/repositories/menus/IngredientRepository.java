package com.uis.ComedoresUIS.repositories.menus;

import com.uis.ComedoresUIS.models.menus.FoodCategory;
import com.uis.ComedoresUIS.models.menus.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> getAllIngredientByFoodCategory(FoodCategory foodCategory);

    Ingredient findByName(String name);
}
