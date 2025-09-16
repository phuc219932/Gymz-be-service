package com.backend_service.gymz.user.dto.Request.user;

import java.util.Date;
import java.util.List;

import com.backend_service.gymz.user.common.enums.user.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String password;
    private List<Integer> roleIds;

    private List<AddressRequest> addresses;

}