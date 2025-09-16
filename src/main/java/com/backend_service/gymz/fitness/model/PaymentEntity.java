package com.backend_service.gymz.fitness.model;

import java.math.BigDecimal;

import com.backend_service.gymz.common.model.AbstractEntity;
import com.backend_service.gymz.fitness.common.enums.payment.PaymentMethod;
import com.backend_service.gymz.fitness.common.enums.payment.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.AssertTrue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_payment")
public class PaymentEntity extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id")
    private MembershipRecord membership; 

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private ContractEntity contract;
   
    @Column(name = "money", nullable = false)
    private BigDecimal money; 

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "description")
    private String description;

    @AssertTrue(message = "Realationship must be either membership or contract")
    public boolean isValidReference() {
        return (membership != null) ^ (contract != null);
    }

    public boolean isForMembership() {
        return membership != null;
    }

    public boolean isForContract() {
        return contract != null;
    }
}
   