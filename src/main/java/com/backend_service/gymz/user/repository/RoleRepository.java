package com.backend_service.gymz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend_service.gymz.user.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
