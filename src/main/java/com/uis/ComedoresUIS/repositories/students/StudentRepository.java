package com.uis.ComedoresUIS.repositories.students;

import com.uis.ComedoresUIS.models.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
