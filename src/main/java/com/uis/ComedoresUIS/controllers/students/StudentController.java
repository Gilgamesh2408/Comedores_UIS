package com.uis.ComedoresUIS.controllers.students;

import com.uis.ComedoresUIS.models.students.Justification;
import com.uis.ComedoresUIS.services.students.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

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
