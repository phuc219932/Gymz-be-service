package com.backend_service.gymz.fitness.service;

import java.util.List;

import com.backend_service.gymz.fitness.dto.request.payment.PaymentCreateRequest;
import com.backend_service.gymz.fitness.dto.request.payment.PaymentUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.payment.PaymentPageResponse;
import com.backend_service.gymz.fitness.dto.response.payment.PaymentResponse;

public interface PaymentService {

    PaymentResponse save(PaymentCreateRequest request);

    PaymentResponse findById(Long id);

    PaymentPageResponse findAll(int page, int size, String sortBy, String direction, boolean ignoreCase);

    List<PaymentResponse> findPaymentByUserId(Long userId);

    PaymentResponse update(PaymentUpdateRequest request);

    void deleteById(Long id);
    
}
