package com.backend_service.gymz.fitness.dto.response.payment;

import java.math.BigDecimal;

import com.backend_service.gymz.fitness.common.enums.payment.PaymentMethod;
import com.backend_service.gymz.fitness.common.enums.payment.PaymentStatus;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class PaymentResponse {
    private Long id;
    private Long membershipId;
    private Long contractId;
    private Long customerId;
    private BigDecimal money;
    private PaymentMethod method;
    private PaymentStatus status;
    private String description;

   
}
