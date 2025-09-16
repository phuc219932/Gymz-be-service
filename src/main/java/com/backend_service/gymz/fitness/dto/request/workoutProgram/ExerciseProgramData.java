package com.backend_service.gymz.fitness.dto.request.workoutProgram;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseProgramData {

    @NotNull(message = "Exercise must be not null")
    private Long exerciseId;
    
    @Min(value = 1, message = "sets must be at least 1.")
    private Integer sets;
    
    @Min(value = 1, message = "reps must be at least 1.")
    private Integer reps;

    private int weight;
    private Integer restTime;
    private Integer orderIndex;
    private String description;
}