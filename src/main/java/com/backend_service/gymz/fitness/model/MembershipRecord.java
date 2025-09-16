package com.backend_service.gymz.fitness.model;

import java.util.Date;

import com.backend_service.gymz.common.model.AbstractEntity;
import com.backend_service.gymz.fitness.common.enums.membership.MembershipRecordStatus;
import com.backend_service.gymz.user.model.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_membership_record")
public class MembershipRecord extends AbstractEntity<Long> {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private UserEntity customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private MembershipEntity packageInfo;
    
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    
    @Column(name = "end_date", nullable = false)
    private Date endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MembershipRecordStatus status;
}    
