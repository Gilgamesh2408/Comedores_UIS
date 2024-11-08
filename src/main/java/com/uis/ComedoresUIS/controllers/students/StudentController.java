package com.uis.ComedoresUIS.controllers.students;

import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.services.students.StudentService;
import com.uis.ComedoresUIS.models.students.Justification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;
import java.time.LocalDate;
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

    @PostMapping("/justification")
    public ResponseEntity<Justification> createJustification(
            Boolean type, LocalDate dateInit, LocalDate dateEnd, String description, Long idStudent
    ) {

        try {
            Justification justification = studentService.createJustification(type, dateInit, dateEnd, description, idStudent);
            return ResponseEntity.created(new URI("/student/justification/" + justification.getId())).body(justification);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
