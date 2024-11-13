package com.uis.ComedoresUIS.controllers.admins;

import com.uis.ComedoresUIS.models.admins.Period;
import com.uis.ComedoresUIS.models.menus.dto.MenuProgrammingDTO;
import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.services.admins.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    //Creación de menus
    @PostMapping("/create/menu")
    public ResponseEntity<MenuProgramming> createMenuProgramming(@RequestBody MenuProgrammingDTO menuProgramming) {
        try {
            MenuProgramming menu = adminService.createMenuProgramming(menuProgramming);
            return ResponseEntity.created(new URI("/admin/create/menu/" + menu.getId())).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
