package com.backend_service.gymz.fitness.dto.response.workSchedule;

import java.util.Date;

import com.backend_service.gymz.fitness.common.enums.schedule.WorkScheduleShift;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkScheduleResponse {
    
    private Long id;
    private Long employeeId;
    private String employeeName;
    private String employeeUsername;
    private Date workDate;
    private Date startTime;
    private Date endTime;
    private WorkScheduleShift shift;

}
