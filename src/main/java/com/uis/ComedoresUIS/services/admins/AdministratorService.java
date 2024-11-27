package com.uis.ComedoresUIS.services.admins;

import com.uis.ComedoresUIS.persistence.models.admins.Administrator;
import com.uis.ComedoresUIS.persistence.models.admins.Date;
import com.uis.ComedoresUIS.persistence.models.admins.Period;
import com.uis.ComedoresUIS.persistence.models.menus.Ingredient;
import com.uis.ComedoresUIS.persistence.models.menus.dto.MealDTO;
import com.uis.ComedoresUIS.persistence.models.menus.dto.MenuProgrammingDTO;
import com.uis.ComedoresUIS.persistence.models.menus.Meal;
import com.uis.ComedoresUIS.persistence.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.persistence.models.menus.TypeMeal;
import com.uis.ComedoresUIS.persistence.repositories.admins.AdministratorRepository;
import com.uis.ComedoresUIS.persistence.repositories.admins.DateRepository;
import com.uis.ComedoresUIS.persistence.repositories.admins.PeriodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdministratorService {

    @Autowired
    private DateRepository dateRepository;
    @Autowired
    private PeriodRepository periodRepository;
    @Autowired
    private MenuService menuService;
    @Autowired
    private AdministratorRepository adminRepository;

    public Date createDate(Date date) {
        return dateRepository.save(date);
    }

    public Period createPeriod(Period period) {
        return periodRepository.save(period);
    }

    public List<Period> getPeriods() {
        return periodRepository.findAll();
    }

    @Transactional
    public MenuProgramming createMenuProgramming(MenuProgrammingDTO menu,
                                                 Principal principal) {
        Administrator admin = adminRepository.findAdministratorByCodeAdmin(principal.getName()).
                orElseThrow(() -> new EntityNotFoundException("Admin not Found"));

        TypeMeal typeMeal = menuService.getTypeMealById(menu.getTypeMeal());

        Meal meal = menuService.getMealById(menu.getMeal());

        MenuProgramming menuProgramming = MenuProgramming.builder()
                .date(menu.getDate())
                .admin(admin)
                .typeMeal(typeMeal)
                .meal(meal)
                .build();

        return menuService.createMenuProgramming(menuProgramming);
    }

    public Meal createMeal(MealDTO mealDTO) {
        LocalDate today = LocalDate.now();
        mealDTO.setCreatedAt(today);
        return menuService.createMeal(mealDTO);
    }

    public List<Meal> getAllMeals() {
        return menuService.getAllMeal();
    }

    public void deleteMealById(Long id) {
        menuService.deleteMealById(id);
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return menuService.createIngredient(ingredient);
    }

}
