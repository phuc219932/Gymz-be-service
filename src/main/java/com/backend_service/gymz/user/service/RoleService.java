package com.backend_service.gymz.user.service;

import com.backend_service.gymz.user.dto.Request.role.RoleCreateRequest;
import com.backend_service.gymz.user.dto.Request.role.RoleUpdateRequest;
import com.backend_service.gymz.user.dto.Response.role.RolePageResponse;
import com.backend_service.gymz.user.dto.Response.role.RoleResponse;

import org.springframework.data.domain.Pageable;

public interface RoleService {

    RoleResponse createRole(RoleCreateRequest request);

    RoleResponse updateRole(RoleUpdateRequest request);

    void deleteRole(Integer id);

    RoleResponse getRoleById(Integer id);
    
    RolePageResponse getRoles(Pageable pageable);
}
