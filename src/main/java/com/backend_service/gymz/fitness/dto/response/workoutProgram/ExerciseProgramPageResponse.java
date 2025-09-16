package com.backend_service.gymz.fitness.dto.response.workoutProgram;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
@Builder
public class ExerciseProgramPageResponse extends PageResponseAbstract {
    private List<ExerciseProgramResponse> exercisePrograms;
}
