package com.backend_service.gymz.user.service;

import com.backend_service.gymz.user.dto.Request.permission.PermissionCreateResquest;
import com.backend_service.gymz.user.dto.Request.permission.PermissionUpdateRequest;
import com.backend_service.gymz.user.dto.Response.permission.PermissionPageReponse;
import com.backend_service.gymz.user.dto.Response.permission.PermissionResponse;

import org.springframework.data.domain.Pageable;

public interface PermissionService {

    PermissionResponse save(PermissionCreateResquest request);

    PermissionResponse findById(Integer id);

    PermissionPageReponse finđAll(Pageable pageable);

    PermissionResponse update(PermissionUpdateRequest request);
    
    void delete(Integer id);
}
