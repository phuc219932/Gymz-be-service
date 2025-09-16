package com.backend_service.gymz.fitness.common.enums.schedule;

import lombok.Getter;

@Getter
public enum WorkScheduleStatus {
    SCHEDULED("Scheduled"),
    COMPLETED("Completed"), 
    CANCELLED("Cancelled");
    
    private final String displayName;
    
    WorkScheduleStatus(String displayName) {
        this.displayName = displayName;
    }
}
