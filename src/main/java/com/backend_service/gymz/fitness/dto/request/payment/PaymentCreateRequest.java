package com.backend_service.gymz.fitness.dto.request.payment;

import java.math.BigDecimal;

import com.backend_service.gymz.fitness.common.enums.payment.PaymentMethod;
import com.backend_service.gymz.fitness.common.enums.payment.PaymentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCreateRequest {
    private Long membershipId;
    private Long contractId;
    // private Long customerId;

    @NotNull(message = "money must be not null")
    private BigDecimal money;
    
    @NotNull(message = "method must be not null")
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @NotNull(message = "status must be not null")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String description;

    @AssertTrue(message = "Realationship must be either membership or contract")
    public boolean isValidReference() {
        return (membershipId != null) ^ (contractId != null);
    }
}
