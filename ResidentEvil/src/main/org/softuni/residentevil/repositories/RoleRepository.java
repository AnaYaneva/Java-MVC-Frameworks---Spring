package org.softuni.residentevil.repositories;

import org.softuni.residentevil.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByAuthority(String authority);
}