package com.backend_service.gymz.fitness.dto.request.workoutProgram;

import java.util.List;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutPlanCreateRequest {
    @NotBlank(message = "Program name must not be blank")
    private String name;

    @NotNull(message = "Contract must not be null")
    private Long contract;

    
    @NotNull(message = "Customer ID must not be null")
    private Long customerId;
    private Integer duration;
    private String description;
    
    @Valid
    private List<ExerciseProgramData> exercises;
}
