package com.backend_service.gymz.fitness.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.backend_service.gymz.common.model.ResponseData;
import com.backend_service.gymz.fitness.dto.request.payment.PaymentCreateRequest;
import com.backend_service.gymz.fitness.dto.request.payment.PaymentUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.payment.PaymentPageResponse;
import com.backend_service.gymz.fitness.dto.response.payment.PaymentResponse;
import com.backend_service.gymz.fitness.service.PaymentService;
import com.backend_service.gymz.user.model.UserEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/payment")
@Tag(name = "Payment Controller")
@RequiredArgsConstructor
@Slf4j(topic = "PAYMENT-CONTROLLER")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Create new payment")
    // Only owner and receptionist can create payments
    @PreAuthorize("hasAuthority('PAYMENT:CREATE:ALL')")
    public ResponseData<PaymentResponse> createPayment(@Valid @RequestBody PaymentCreateRequest req) {
        log.info("Creating payment: {}", req);
        PaymentResponse data = paymentService.save(req);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Payment created successfully", data);
    }

    @GetMapping
    @Operation(summary = "Get list of all payments")
    // Only owner and receptionist can view all payments
    @PreAuthorize("hasAuthority('PAYMENT:READ:ALL')")
    public ResponseData<PaymentPageResponse> getAllPayments( 
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "ASC") String direction,
        @RequestParam(defaultValue = "false") boolean ignoreCase) {
        log.info("Fetching all payments");
        PaymentPageResponse data = paymentService.findAll(page, size, sortBy, direction, ignoreCase);
        return new ResponseData<>(HttpStatus.OK.value(), "Payments retrieved successfully", data);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment by ID")
    // Only owner and receptionist can view payment by ID
    @PreAuthorize("hasAuthority('PAYMENT:READ:ALL')")
    public ResponseData<PaymentResponse> getPaymentById(@PathVariable Long id) {
        log.info("Fetching payment by ID: {}", id);
        PaymentResponse data = paymentService.findById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Payment retrieved successfully", data);
    }

    @GetMapping("/me")
    @Operation(summary = "Get payment of logged-in user")
    @PreAuthorize("hasAuthority('PAYMENT:READ:SELF')")
    public ResponseData<?> getMyPayment(@AuthenticationPrincipal UserEntity user) {
                
        Long userId = user.getId();
        log.info("Fetching payment for logged-in user: {}", userId);
        List<PaymentResponse> data = paymentService.findPaymentByUserId(userId);
        return new ResponseData<>(HttpStatus.OK.value(), "Payment retrieved successfully", data);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update payment by ID")
    // Only owner and receptionist can update payments
    @PreAuthorize("hasAuthority('PAYMENT:UPDATE:ALL')")
    public ResponseData<PaymentResponse> updatePayment(
            @PathVariable Long id,
            @Valid @RequestBody PaymentUpdateRequest req) {
        log.info("Updating payment with ID: {}", id);
        req.setId(id);
        PaymentResponse data = paymentService.update(req);
        return new ResponseData<>(HttpStatus.OK.value(), "Payment updated successfully", data);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Refund payment by ID")
    // Only owner and receptionist can refund (delete) payments
    @PreAuthorize("hasAuthority('PAYMENT:DELETE:ALL')")
    public ResponseData<Void> refundPayment(@PathVariable Long id) {
        log.info("Refunding payment with ID: {}", id);
        paymentService.deleteById(id);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Payment refunded successfully");
    }
}
