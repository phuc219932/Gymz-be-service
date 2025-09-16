package com.backend_service.gymz.fitness.dto.response.workoutProgram;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseProgramResponse {
    private Long exerciseId;
    private String exerciseName;
    private String exerciseDescription;
    private Integer sets;
    private Integer reps;
    private Integer weight;
    private Integer restTime;
    private Integer orderIndex;
    private String description;
}
