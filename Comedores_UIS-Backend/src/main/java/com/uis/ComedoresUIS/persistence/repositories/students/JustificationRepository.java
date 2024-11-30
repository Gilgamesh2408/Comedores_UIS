package com.uis.ComedoresUIS.persistence.repositories.students;

import com.uis.ComedoresUIS.persistence.models.students.Justification;
import com.uis.ComedoresUIS.persistence.models.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JustificationRepository extends JpaRepository<Justification, Long> {

    List<Justification> findAllJustificationsByStudent(Student student);

}
