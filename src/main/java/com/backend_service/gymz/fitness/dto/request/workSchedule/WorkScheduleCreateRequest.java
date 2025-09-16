package com.backend_service.gymz.fitness.dto.request.workSchedule;

import java.util.Date;

import com.backend_service.gymz.fitness.common.enums.schedule.WorkScheduleShift;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkScheduleCreateRequest {
    
    @NotNull(message = "Employee ID must not be null")
    private Long employeeId;
    
    @NotNull(message = "Work date must not be null")
    private Date workDate;
    
    @NotNull(message = "Start time must not be null")
    private Date startTime;
    
    @NotNull(message = "End time must not be null")
    private Date endTime;
    
    @NotNull(message = "Shift must not be null")
    private WorkScheduleShift shift;
}
