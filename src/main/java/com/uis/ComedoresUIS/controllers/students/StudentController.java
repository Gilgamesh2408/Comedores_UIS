package com.uis.ComedoresUIS.controllers.students;

import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.services.students.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{day}")
    public ResponseEntity<List<MenuProgramming>> getMenu(@PathVariable String day) {
        return ResponseEntity.ok(studentService.getMenuByDay(day));
    }

}
