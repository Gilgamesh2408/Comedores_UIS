package com.uis.ComedoresUIS.services.students;

import com.uis.ComedoresUIS.models.students.Justification;
import com.uis.ComedoresUIS.models.students.Student;
import com.uis.ComedoresUIS.repositories.students.JustificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JustificationService {

    @Autowired
    private JustificationRepository justificationRepository;

    public Justification createJustification(Justification justification) {
        return justificationRepository.save(justification);
    }


}
