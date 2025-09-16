package com.backend_service.gymz.fitness.dto.request.contract;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractCreateRequest {
    @NotBlank(message = "Name must be not blank")
    private String name;

    @NotNull(message = "customer_id must be not null")
    private Long customer;

    @NotNull(message = "pt_id must be not null")
    private Long pt;

    @NotNull(message = "duration must be not null")
    private int duration;

    @NotNull(message = "start_date must be not null")
    private Date startDate;

    // @NotNull(message = "end_date must be not null")
    // private Date endDate;

    @NotNull(message = "Money must be not nulls")
    private BigDecimal money;
}
