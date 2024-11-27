package com.uis.ComedoresUIS.persistence.repositories.students;

import com.uis.ComedoresUIS.persistence.models.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByCodeStudent(String codeStudent);

}
