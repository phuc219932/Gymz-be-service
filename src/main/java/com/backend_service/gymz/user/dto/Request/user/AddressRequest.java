package com.backend_service.gymz.user.dto.Request.user;

import com.backend_service.gymz.user.model.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    private String street;
    private String city;
    private String country;
    private UserEntity user;
    private Integer addressType;
}
