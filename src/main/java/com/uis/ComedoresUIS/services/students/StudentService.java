package com.uis.ComedoresUIS.services.students;

import com.uis.ComedoresUIS.models.students.Justification;
import com.uis.ComedoresUIS.models.students.Student;
import com.uis.ComedoresUIS.repositories.students.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JustificationService justificationService;

    public Justification createJustification(
            Boolean type, LocalDate dateInit, LocalDate dateEnd, String description, Long idStudent
    ) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("Not found Student"));

        Justification justification = new Justification(null, type, dateInit, dateEnd, description, student);
        return justificationService.createJustification(justification);
    }


}
