package com.uis.ComedoresUIS.services.students;

import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.services.admins.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private MenuService menuService;

    public List<MenuProgramming> getMenuByDay(String day) {
        return menuService.getMenuByDay(day);
    }

}
