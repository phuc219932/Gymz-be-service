package com.backend_service.gymz.fitness.dto.response.workoutProgram;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
public class WorkoutProgramPageResponse extends PageResponseAbstract {
    private List<WorkoutProgramResponse> workoutPrograms;
}
