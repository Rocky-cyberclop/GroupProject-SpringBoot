package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teenboutique.web.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
