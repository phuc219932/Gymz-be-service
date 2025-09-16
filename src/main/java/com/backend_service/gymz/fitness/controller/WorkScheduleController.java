package com.backend_service.gymz.fitness.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend_service.gymz.common.model.ResponseData;
import com.backend_service.gymz.fitness.dto.request.workSchedule.WorkScheduleCreateRequest;
import com.backend_service.gymz.fitness.dto.request.workSchedule.WorkScheduleUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.workSchedule.WorkSchedulePageResponse;
import com.backend_service.gymz.fitness.dto.response.workSchedule.WorkScheduleResponse;
import com.backend_service.gymz.fitness.service.WorkScheduleService;
import com.backend_service.gymz.user.model.UserEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/work-schedules")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Work Schedule Management")
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    @PostMapping
    // Only owner and manager can create work schedules
    @PreAuthorize("hasAuthority('WORK_SCHEDULE:CREATE:ALL')")
    @Operation(summary = "Create new work schedule")
    public ResponseData<WorkScheduleResponse> createWorkSchedule(@Valid @RequestBody WorkScheduleCreateRequest request) {
        log.info("Received request to create work schedule for employee ID: {}", request.getEmployeeId());
        
        WorkScheduleResponse response = workScheduleService.save(request);
        log.info("Successfully created work schedule with ID: {}", response.getId());
        
        return new ResponseData<>(HttpStatus.CREATED.value(), "Work schedule created successfully", response);
    }

    @GetMapping("/{id}")
    // All roles can view work schedule by ID
    @PreAuthorize("hasAuthority('WORK_SCHEDULE:READ:ALL') or hasAuthority('WORK_SCHEDULE:READ:SELF')")
    @Operation(summary = "Get work schedule by ID")
    public ResponseData<WorkScheduleResponse> getWorkSchedule(@PathVariable Long id) {
        log.info("Received request to get work schedule with ID: {}", id);
        
        WorkScheduleResponse response = workScheduleService.findById(id);
        log.info("Successfully retrieved work schedule for employee: {}", response.getEmployeeName());
        
        return new ResponseData<>(HttpStatus.OK.value(), "Work schedule retrieved successfully", response);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('WORK_SCHEDULE:READ:SELF')")

    @Operation(summary = "Get work-plan of logged-in user")
    public ResponseData<List<WorkScheduleResponse>> getWorkScheduleByUser(@AuthenticationPrincipal UserEntity user) {
        
        Long userId = user.getId();
        List<WorkScheduleResponse> data = workScheduleService.findByUserId(userId);
        
        return new ResponseData<>(HttpStatus.OK.value(), "Work schedule retrieved successfully", data);
    }
    

    @GetMapping
    // All roles can view the list of work schedules
    @PreAuthorize("hasAuthority('WORK_SCHEDULE:READ:ALL')")
    @Operation(summary = "Get all work schedules")
    public ResponseData<WorkSchedulePageResponse> getAllWorkSchedules(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "ASC") String direction,
        @RequestParam(defaultValue = "false") boolean ignoreCase
    ) {
        log.info("Received request to get all work schedules");
        
        WorkSchedulePageResponse response = workScheduleService.findAll(page, size, sortBy, direction, ignoreCase);
        log.info("Successfully retrieved {} work schedules");
        
        return new ResponseData<>(HttpStatus.OK.value(), "Work schedules retrieved successfully", response);
    }

    @PutMapping("/{id}")
    // Only owner and manager can update work schedules
    @PreAuthorize("hasAuthority('WORK_SCHEDULE:UPDATE:ALL')")
    @Operation(summary = "Update work schedule")
    public ResponseData<WorkScheduleResponse> updateWorkSchedule(@PathVariable Long id, @Valid @RequestBody WorkScheduleUpdateRequest request) {
        log.info("Received request to update work schedule with ID: {}", id);
        
        WorkScheduleResponse response = workScheduleService.update(id, request);
        log.info("Successfully updated work schedule with ID: {}", id);
        
        return new ResponseData<>(HttpStatus.OK.value(), "Work schedule updated successfully", response);
    }

    @DeleteMapping("/{id}")
    // Only owner and manager can delete work schedules
    @PreAuthorize("hasAuthority('WORK_SCHEDULE:DELETE:ALL')")
    @Operation(summary = "Delete work schedule")
    public ResponseData<Void> deleteWorkSchedule(@PathVariable Long id) {
        log.info("Received request to delete work schedule with ID: {}", id);
        
        workScheduleService.deleteById(id);
        log.info("Successfully deleted work schedule with ID: {}", id);
        
        return new ResponseData<>(HttpStatus.OK.value(), "Work schedule deleted successfully", null);
    }
}
