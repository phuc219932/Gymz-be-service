package com.backend_service.gymz.fitness.dto.response.contract;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractResponse {
    private String name;
    private Long customer;
    private Long pt;
    private int duration;
    private Date startDate;
    private Date endDate;
    private BigDecimal money;
}
