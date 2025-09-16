package com.backend_service.gymz.fitness.dto.response.exercise;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
public class ExercisePageResponse extends PageResponseAbstract {
    private List<ExerciseResponse> exercises;
}
