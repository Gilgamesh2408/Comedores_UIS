package com.uis.ComedoresUIS.services.students;

import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.services.admins.MenuService;
import com.uis.ComedoresUIS.models.students.Justification;
import com.uis.ComedoresUIS.models.students.Student;
import com.uis.ComedoresUIS.repositories.students.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private MenuService menuService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JustificationService justificationService;


    public List<MenuProgramming> getMenuByDay(String day) {
        return menuService.getMenuByDay(day);
    }

    public Justification createJustification(
            Boolean type, LocalDate dateInit, LocalDate dateEnd, String description, Long idStudent
    ) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("Not found Student"));

        Justification justification = new Justification(null, type, dateInit, dateEnd, description, student);
        return justificationService.createJustification(justification);
    }

}
