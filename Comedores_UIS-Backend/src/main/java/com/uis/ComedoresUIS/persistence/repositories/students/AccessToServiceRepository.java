package com.uis.ComedoresUIS.persistence.repositories.students;

import com.uis.ComedoresUIS.persistence.models.students.AccessToService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessToServiceRepository extends JpaRepository<AccessToService, Long> {
}
