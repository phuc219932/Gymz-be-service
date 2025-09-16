package com.backend_service.gymz.fitness.dto.request.workoutProgram;

import java.util.List;

import com.backend_service.gymz.fitness.model.ContractEntity;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutPlanExerciseUpdateRequest {
    private Long id;
    private String name;
    private Long customerId;
    private Integer duration;
    private String description;
    private ContractEntity contract;

    @Valid
    private List<ExerciseProgramData> exercises;
}
