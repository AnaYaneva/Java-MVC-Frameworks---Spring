package org.softuni.residentevil.repositories;

import org.softuni.residentevil.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {
    Capital getCapitalByName(String name);
}
