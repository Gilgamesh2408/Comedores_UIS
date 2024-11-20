package com.uis.ComedoresUIS.repositories.admins;

import com.uis.ComedoresUIS.models.admins.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Optional<Administrator> findAdministratorByCodeAdmin(String codeAdmin);

}
