package com.uis.ComedoresUIS.services.admins;

import com.uis.ComedoresUIS.persistence.models.menus.*;
import com.uis.ComedoresUIS.persistence.models.menus.dto.MealDTO;
import com.uis.ComedoresUIS.persistence.repositories.menus.IngredientRepository;
import com.uis.ComedoresUIS.persistence.repositories.menus.MealRepository;
import com.uis.ComedoresUIS.persistence.repositories.menus.MenuProgrammingRepository;
import com.uis.ComedoresUIS.persistence.repositories.menus.TypeMealRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class MenuService {

    private final MenuProgrammingRepository menuProgrammingRepository;
    private final TypeMealRepository typeMealRepository;
    private final MealRepository mealRepository;
    private final IngredientRepository ingredientRepository;

    public MenuService(MenuProgrammingRepository menuProgrammingRepository,
                       TypeMealRepository typeMealRepository,
                       MealRepository mealRepository,
                       IngredientRepository ingredientRepository) {
        this.menuProgrammingRepository = menuProgrammingRepository;
        this.typeMealRepository = typeMealRepository;
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
    }

    //CRUD for MenuProgramming
    public MenuProgramming createMenuProgramming(MenuProgramming menu) {
        return menuProgrammingRepository.save(menu);
    }

    public void deleteMenuProgrammingById(Long id) {
        menuProgrammingRepository.deleteById(id);
    }

    public List<MenuProgramming> getMenuByDay(String date) {
        LocalDate today = LocalDate.now();
        date = date.toUpperCase();
        LocalDate day = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.valueOf(date)));
        return menuProgrammingRepository.findAll().stream().filter(
                menu -> menu.getDate().getDayOfMonth() == day.getDayOfMonth())
                .toList();
    }

    public TypeMeal createTypeMeal(TypeMeal typeMeal) {
        return typeMealRepository.save(typeMeal);
    }

    protected TypeMeal getTypeMealById(Long id) {
        return typeMealRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("TypeMeal not Found"));
    }

    public List<TypeMeal> getAllTypeMeal() {
        return typeMealRepository.findAll();
    }

    @Transactional
    public Meal createMeal(MealDTO mealDTO) {

        Meal meal = new Meal();
        meal.setMainCourse(mealDTO.getMainCourse());
        meal.setSoup(mealDTO.getSoup());
        meal.setDrink(mealDTO.getDrink());
        meal.setDessert(mealDTO.getDessert());
        meal.setCreatedAt(mealDTO.getCreatedAt());

        List<MealIngredient> ingredients = mealDTO.getIngredientsDTO().stream()
                .map(ingredientDTO -> {
                    Ingredient ingredient = ingredientRepository.findByName(ingredientDTO.getName());
                    MealIngredient mealIngredient = new MealIngredient();

                    mealIngredient.setMeal(meal);
                    mealIngredient.setIngredient(ingredient);
                    mealIngredient.setQuantity(ingredientDTO.getQuantity());
                    return mealIngredient;
                })
                .toList();

        meal.setIngredients(ingredients);
        return mealRepository.save(meal);
    }

    protected Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not Found"));
    }

    public List<Meal> getAllMeal() {
        return mealRepository.findAll();
    }

    public void deleteMealById(Long id) {
        mealRepository.deleteById(id);
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    //Crear el join de Meal e Ingredient

}
