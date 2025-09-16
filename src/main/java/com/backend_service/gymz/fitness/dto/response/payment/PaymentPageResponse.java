package com.backend_service.gymz.fitness.dto.response.payment;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
public class PaymentPageResponse extends PageResponseAbstract {
    private List<PaymentResponse> payments;
}
