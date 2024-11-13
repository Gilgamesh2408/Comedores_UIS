package com.uis.ComedoresUIS.services.students;

import com.uis.ComedoresUIS.models.menus.MenuProgramming;
import com.uis.ComedoresUIS.models.students.JustificationDTO;
import com.uis.ComedoresUIS.services.admins.MenuService;
import com.uis.ComedoresUIS.models.students.Justification;
import com.uis.ComedoresUIS.models.students.Student;
import com.uis.ComedoresUIS.repositories.students.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

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

    public Justification createJustification(JustificationDTO justificationDTO) {

        Student student = studentRepository.findById(justificationDTO.getStudent())
                .orElseThrow(() -> new EntityNotFoundException("Student noy Found"));

        Justification justification = Justification.builder()
                .student(student)
                .type(justificationDTO.getType())
                .description(justificationDTO.getDescription())
                .dateInit(justificationDTO.getDateInit())
                .dateEnd(justificationDTO.getDateEnd())
                .build();

        return justificationService.createJustification(justification);
    }

    public List<Justification> getAllJustificationsByStudent(Long idStudent) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("Student not Found"));

        return justificationService.getAllJustificationsByStudent(student);
    }

    public void deleteJustificationById(Long idJustification) {
        justificationService.deleteJustificationById(idJustification);
    }

}
