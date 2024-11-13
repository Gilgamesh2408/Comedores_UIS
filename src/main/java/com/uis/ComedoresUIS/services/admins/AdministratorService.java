package com.uis.ComedoresUIS.services.admins;

import com.uis.ComedoresUIS.models.admins.Administrator;
import com.uis.ComedoresUIS.models.admins.Date;
import com.uis.ComedoresUIS.models.admins.Period;
import com.uis.ComedoresUIS.models.menus.DTO.MenuProgrammingDTO;
import com.uis.ComedoresUIS.models.menus.Meal;
import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.models.menus.TypeMeal;
import com.uis.ComedoresUIS.repositories.admins.AdministratorRepository;
import com.uis.ComedoresUIS.repositories.admins.DateRepository;
import com.uis.ComedoresUIS.repositories.admins.PeriodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public MenuProgramming createMenuProgramming(MenuProgrammingDTO menu) {
        Administrator admin = adminRepository.findById(menu.getAdmin()).
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

    public Meal createMeal(String mainCourse, String soup, String drink, String dessert) {
        Meal meal = new Meal(null, mainCourse, soup, drink, dessert);
        return menuService.createMeal(meal);
    }

}
