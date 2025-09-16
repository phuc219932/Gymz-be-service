package com.backend_service.gymz.user.service;

import com.backend_service.gymz.user.dto.Request.user.UserCreateRequest;
import com.backend_service.gymz.user.dto.Request.user.UserPasswordRequest;
import com.backend_service.gymz.user.dto.Request.user.UserUpdateRequest;
import com.backend_service.gymz.user.dto.Response.user.UserPageResponse;
import com.backend_service.gymz.user.dto.Response.user.UserResponse;


public interface UserService {
    UserResponse save(UserCreateRequest request);

    void update(UserUpdateRequest request);

    void changePwd(UserPasswordRequest request);

    void deleteById(Long id);

    UserPageResponse findAllUsers(int page, int size, String sortBy, String direction, boolean ignoreCase);
    
    UserResponse findById(Long userID);
}
