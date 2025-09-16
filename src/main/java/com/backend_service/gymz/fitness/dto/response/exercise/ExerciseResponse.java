package com.backend_service.gymz.fitness.dto.response.exercise;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseResponse {
    private Long id;
    private String name;
    private String description;

}