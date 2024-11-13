package com.uis.ComedoresUIS.repositories.students;

import com.uis.ComedoresUIS.models.students.Justification;
import com.uis.ComedoresUIS.models.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JustificationRepository extends JpaRepository<Justification, Long> {

    List<Justification> findAllJustificationsByStudent(Student student);

}
