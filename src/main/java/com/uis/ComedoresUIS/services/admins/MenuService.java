package com.uis.ComedoresUIS.services.admins;

import com.uis.ComedoresUIS.models.menus.Meal;
import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.models.menus.TypeMeal;
import com.uis.ComedoresUIS.repositories.menus.MealIngredientRepository;
import com.uis.ComedoresUIS.repositories.menus.MealRepository;
import com.uis.ComedoresUIS.repositories.menus.MenuProgrammingRepository;
import com.uis.ComedoresUIS.repositories.menus.TypeMealRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuProgrammingRepository menuProgrammingRepository;
    @Autowired
    private TypeMealRepository typeMealRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private MealIngredientRepository mealIngredientRepository;

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

    public List<TypeMeal> getAlTypeMeal() {
        return typeMealRepository.findAll();
    }

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    protected Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not Found"));
    }

    //Crear el join de Meal e Ingredient

}
