package com.uis.ComedoresUIS.services.admins;

import com.uis.ComedoresUIS.models.menus.FoodCategory;
import com.uis.ComedoresUIS.models.menus.Ingredient;
import com.uis.ComedoresUIS.repositories.menus.FoodCategoryRepository;
import com.uis.ComedoresUIS.repositories.menus.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> getAllIngredientByCategory(FoodCategory foodCategory) {
        return ingredientRepository.getAllIngredientByFoodCategory(foodCategory);
    }

    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }

    public FoodCategory createFoodCategory(FoodCategory category) {
        return foodCategoryRepository.save(category);
    }

    public void deleteFoodCategoryById(Long id) {
        foodCategoryRepository.deleteById(id);
    }

}
