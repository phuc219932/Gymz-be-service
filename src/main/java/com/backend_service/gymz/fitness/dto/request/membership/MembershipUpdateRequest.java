package com.backend_service.gymz.fitness.dto.request.membership;

import java.math.BigDecimal;

import com.backend_service.gymz.fitness.common.enums.membership.MembershipStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipUpdateRequest {

    @NotNull(message = "ID must not be null")
    private Long id;

    @NotNull(message = "status must not be blank")
    private MembershipStatus status;

    @NotBlank(message = "name must be not blank")
    private String name;

    @NotNull(message = "duration must be not null")
    private Integer duration;

    @NotNull(message = "Price must be not null")
    private BigDecimal price;

    private String description;
}
