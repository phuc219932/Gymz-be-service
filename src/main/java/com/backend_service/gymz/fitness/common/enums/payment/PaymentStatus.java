package com.backend_service.gymz.fitness.common.enums.payment;

public enum PaymentStatus {
    PENDING("Chờ thanh toán"),
    COMPLETED("Đã thanh toán"),
    FAILED("Thất bại"),
    REFUNDED("Đã hoàn tiền");
    
    private String displayName;
    
    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}