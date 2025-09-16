package com.backend_service.gymz.fitness.model;

import java.util.Date;

import com.backend_service.gymz.common.model.AbstractEntity;
import com.backend_service.gymz.fitness.common.enums.schedule.WorkScheduleShift;
import com.backend_service.gymz.user.model.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_work_schedule")
public class WorkScheduleEntity extends AbstractEntity<Long> {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private UserEntity employee;
    
    @Column(name = "work_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date workDate;
    
    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date startTime;
    
    @Column(name = "end_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date endTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "shift", nullable = false, length = 20)
    private WorkScheduleShift shift;
    
}
