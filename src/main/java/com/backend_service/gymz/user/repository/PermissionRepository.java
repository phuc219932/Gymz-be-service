package com.backend_service.gymz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend_service.gymz.user.model.Permission;

@Repository
public interface PermissionRepository  extends JpaRepository<Permission, Integer>{
    // boolean existsByName(String name);
}
