package com.uis.ComedoresUIS.controllers.students;

import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.models.students.dto.JustificationDTO;
import com.uis.ComedoresUIS.services.students.StudentService;
import com.uis.ComedoresUIS.models.students.Justification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.net.URI;
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

    @GetMapping("/justification/{idStudent}")
    public ResponseEntity<List<Justification>> getJustifications(@PathVariable Long idStudent) { //THIS IS A TEST
        return ResponseEntity.ok(studentService.getAllJustificationsByStudent(idStudent));
    }

    @PostMapping("/justification")
    public ResponseEntity<Justification> createJustification(@RequestBody JustificationDTO justification) {
        try {
            Justification j = studentService.createJustification(justification);
            return ResponseEntity.created(new URI("/student/justification/" + j.getId())).body(j);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
