package com.backend_service.gymz.fitness.service;

import java.util.List;

import com.backend_service.gymz.fitness.dto.request.workoutProgram.WorkoutPlanCreateRequest;
import com.backend_service.gymz.fitness.dto.response.workoutProgram.WorkoutProgramPageResponse;
import com.backend_service.gymz.fitness.dto.response.workoutProgram.WorkoutProgramResponse;

public interface WorkoutPlanService {

    WorkoutProgramResponse save(WorkoutPlanCreateRequest req);

    WorkoutProgramResponse findById(Long id);

    WorkoutProgramPageResponse findAll(int page, int size, String sortBy, String direction, boolean ignoreCase);

    List<WorkoutProgramResponse> findWorkPlansByUserId(Long userId);

    WorkoutProgramResponse update(Long id, WorkoutPlanCreateRequest program);

    void deleteById(Long id);
}
