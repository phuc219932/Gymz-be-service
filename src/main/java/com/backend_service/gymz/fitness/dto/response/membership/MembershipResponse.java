package com.backend_service.gymz.fitness.dto.response.membership;

import java.math.BigDecimal;

import com.backend_service.gymz.fitness.common.enums.membership.MembershipStatus;

import lombok.Data;

@Data
public class MembershipResponse {
    
    private Long id;
    
    private String name;
    
    private Integer duration;

    private BigDecimal price;

    private String description;
    
    private MembershipStatus status;
    
}
