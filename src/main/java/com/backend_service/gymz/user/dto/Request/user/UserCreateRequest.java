package com.backend_service.gymz.user.dto.Request.user;

import java.util.Date;
import java.util.List;

import com.backend_service.gymz.user.common.enums.user.Gender;
import com.backend_service.gymz.user.common.enums.user.UserType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    @NotBlank(message = "firstName must be not blank")
    private String firstName;

    @NotBlank(message = "lastName must be not blank")
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String username;
    private String password;
    private String email;
    private UserType userType;
    private List<Integer> roleIds;
    @NotEmpty(message = "addrest must be not empty")
    List<AddressRequest> addresses;
}
