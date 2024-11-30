package com.uis.ComedoresUIS.services.students;

import com.uis.ComedoresUIS.persistence.models.students.Justification;
import com.uis.ComedoresUIS.persistence.models.students.Student;
import com.uis.ComedoresUIS.persistence.repositories.students.JustificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JustificationService {

    @Autowired
    private JustificationRepository justificationRepository;

    public Justification createJustification(Justification justification) {
        return justificationRepository.save(justification);
    }

    public void deleteJustificationById(Long idJustification) {
        justificationRepository.deleteById(idJustification);
    }

    public List<Justification> getAllJustificationsByStudent(Student student) {
        return justificationRepository.findAllJustificationsByStudent(student);
    }

}
