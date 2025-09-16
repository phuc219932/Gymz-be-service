package com.backend_service.gymz.fitness.dto.request.exercise;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseCreateRequest {
    @NotBlank(message = "name exercise must be not blank")
    private String name;
    private String description;
}
