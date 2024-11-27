package com.uis.ComedoresUIS.persistence.repositories.admins;

import com.uis.ComedoresUIS.persistence.models.admins.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, Long> {
}
