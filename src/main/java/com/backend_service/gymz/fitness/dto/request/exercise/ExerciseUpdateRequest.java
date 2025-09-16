package com.backend_service.gymz.fitness.dto.request.exercise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseUpdateRequest {
    private Long id;
    private String name;
    private String description;
}
