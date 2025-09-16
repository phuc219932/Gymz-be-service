package com.backend_service.gymz.fitness.common.enums.schedule;

import lombok.Getter;

@Getter
public enum WorkScheduleShift {
    MORNING("Morning"),
    AFTERNOON("Afternoon"),
    EVENING("Evening");
    
    private final String displayName;
    
    WorkScheduleShift(String displayName) {
        this.displayName = displayName;
    }
}
