package com.backend_service.gymz.fitness.dto.request.membership;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipRecordCreateRequest {
    @NotNull(message = "customer must be not null")
    private Long customer;
    
    @NotNull(message = "membership must be not null")
    private Long packageInfo;
    
    @NotNull(message = "startDate must be not null")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
}
