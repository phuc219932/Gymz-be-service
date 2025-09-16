package com.backend_service.gymz.fitness.service;

import com.backend_service.gymz.fitness.dto.request.exercise.ExerciseCreateRequest;
import com.backend_service.gymz.fitness.dto.request.exercise.ExerciseUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.exercise.ExercisePageResponse;
import com.backend_service.gymz.fitness.dto.response.exercise.ExerciseResponse;

public interface ExerciseService {
    ExerciseResponse save(ExerciseCreateRequest request);

    ExerciseResponse findById(Long id);

    ExercisePageResponse findAll(int page, int size, String sortBy, String direction, boolean ignoreCase);

    ExerciseResponse update(Long id, ExerciseUpdateRequest request);
    
    void deleteById(Long id);
    
}
