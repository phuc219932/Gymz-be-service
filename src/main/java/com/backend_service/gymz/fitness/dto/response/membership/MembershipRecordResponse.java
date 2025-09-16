package com.backend_service.gymz.fitness.dto.response.membership;

import java.util.Date;

import com.backend_service.gymz.fitness.common.enums.membership.MembershipRecordStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipRecordResponse {
    private Long id;
    private Long customer;
    private Long packageInfo;
    private Date startDate;
    private Date endDate;
    private MembershipRecordStatus status;
}
