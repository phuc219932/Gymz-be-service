package com.backend_service.gymz.fitness.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.backend_service.gymz.common.model.AbstractEntity;
import com.backend_service.gymz.user.model.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_contract")
public class ContractEntity extends AbstractEntity<Long>{
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_id")
    private UserEntity pt;    

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    private List<WorkoutPlanEntity> workPlans;

    @OneToOne(mappedBy = "contract", fetch = FetchType.LAZY)
    private PaymentEntity payment;

    private int duration;
    private Date startDate;
    private Date endDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal money;
}
