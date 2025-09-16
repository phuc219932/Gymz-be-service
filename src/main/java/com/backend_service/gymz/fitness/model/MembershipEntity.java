package com.backend_service.gymz.fitness.model;

import java.math.BigDecimal;

import com.backend_service.gymz.common.model.AbstractEntity;
import com.backend_service.gymz.fitness.common.enums.membership.MembershipStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_membership")
public class MembershipEntity extends AbstractEntity<Long> {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "description", length = 500)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MembershipStatus status;

}