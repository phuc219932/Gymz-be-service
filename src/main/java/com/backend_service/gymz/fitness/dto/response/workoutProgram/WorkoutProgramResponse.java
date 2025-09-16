package com.backend_service.gymz.fitness.dto.response.workoutProgram;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkoutProgramResponse {
    private Long id;
    private String name;
    private Long customerId;
    private String customerName;
    private Integer duration;
    private String description;
    private List<ExerciseProgramResponse> exercises;
}
