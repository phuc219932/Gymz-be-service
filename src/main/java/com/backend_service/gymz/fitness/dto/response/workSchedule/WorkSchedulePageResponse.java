package com.backend_service.gymz.fitness.dto.response.workSchedule;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
public class WorkSchedulePageResponse extends PageResponseAbstract {
    private List<WorkScheduleResponse> workSchedules;
}
