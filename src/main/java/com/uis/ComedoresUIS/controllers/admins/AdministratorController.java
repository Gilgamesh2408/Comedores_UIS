package com.uis.ComedoresUIS.controllers.admins;

import com.uis.ComedoresUIS.models.admins.Period;
import com.uis.ComedoresUIS.models.menus.Ingredient;
import com.uis.ComedoresUIS.models.menus.Meal;
import com.uis.ComedoresUIS.models.menus.dto.MealDTO;
import com.uis.ComedoresUIS.models.menus.dto.MenuProgrammingDTO;
import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.services.admins.AdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    private AdministratorService adminService;

    @GetMapping("/period")
    public ResponseEntity<List<Period>> getPeriods() {
        return ResponseEntity.ok(adminService.getPeriods());
    }

    @PostMapping("/create/period")
    public ResponseEntity<Period> createPeriod(@RequestBody Period period) {
        try {
            Period p = adminService.createPeriod(period);
            return ResponseEntity.created(new URI("/admin/" + p.getId())).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Creation of programming menu
    @PostMapping("/create/programming")
    public ResponseEntity<MenuProgramming> createMenuProgramming(@RequestBody MenuProgrammingDTO menuProgramming) {
        try {
            MenuProgramming menu = adminService.createMenuProgramming(menuProgramming);
            return ResponseEntity.created(new URI("/admin/create/menu/" + menu.getId())).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/menus")
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(adminService.getAllMeals());
    }

    @PostMapping("/create/menu")
    public ResponseEntity<Meal> createMeal(@RequestBody MealDTO mealDTO) {
        try {
            Meal m = adminService.createMeal(mealDTO);
            return ResponseEntity.created(new URI("/admin/create/menu/" + m.getId())).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/menus/{id}")
    public void deleteMealById(@PathVariable Long id) {
        adminService.deleteMealById(id);
    }

    @PostMapping("/create/ingredient")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        try {
            Ingredient ingr = adminService.createIngredient(ingredient);
            return ResponseEntity.created(new URI("/admin/create/ingredient/" + ingr.getId())).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
