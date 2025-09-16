package com.backend_service.gymz.fitness.service;

import java.util.List;

import com.backend_service.gymz.fitness.dto.request.workSchedule.WorkScheduleCreateRequest;
import com.backend_service.gymz.fitness.dto.request.workSchedule.WorkScheduleUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.workSchedule.WorkSchedulePageResponse;
import com.backend_service.gymz.fitness.dto.response.workSchedule.WorkScheduleResponse;

public interface WorkScheduleService {

    WorkScheduleResponse save(WorkScheduleCreateRequest request);

    WorkScheduleResponse findById(Long id);

    WorkSchedulePageResponse findAll(int page, int size, String sortBy, String direction, boolean ignoreCase);

    List<WorkScheduleResponse> findByUserId(Long id);


    WorkScheduleResponse update(Long id, WorkScheduleUpdateRequest request);

    void deleteById(Long id);
}
