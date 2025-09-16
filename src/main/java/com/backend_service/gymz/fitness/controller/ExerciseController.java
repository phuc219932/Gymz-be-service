package com.backend_service.gymz.fitness.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.backend_service.gymz.fitness.dto.request.exercise.ExerciseCreateRequest;
import com.backend_service.gymz.fitness.dto.request.exercise.ExerciseUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.exercise.ExercisePageResponse;
import com.backend_service.gymz.fitness.dto.response.exercise.ExerciseResponse;
import com.backend_service.gymz.fitness.service.ExerciseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/exercises")
@RequiredArgsConstructor
@Slf4j(topic = "EXERCISE-CONTROLLER")
@Tag(name = "Exercise Management", description = "APIs for managing exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    // Only owner and manager can create exercises
    @PreAuthorize("hasAuthority('EXERCISE:CREATE:ALL')")
    @PostMapping
    @Operation(summary = "Create new exercise")
    public ResponseData<ExerciseResponse> createExercise(@Valid @RequestBody ExerciseCreateRequest request) {
        log.info("Creating exercise with name: {}", request.getName());

        ExerciseResponse response = exerciseService.save(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Exercise created successfully", response);

    }

    // Only owner and manager can update exercises
    @PreAuthorize("hasAuthority('EXERCISE:UPDATE:ALL')")
    @PutMapping("/{id}")
    @Operation(summary = "Update exercise")
    public ResponseData<ExerciseResponse> updateExercise(
            @PathVariable Long id,
            @Valid @RequestBody ExerciseUpdateRequest request) {
        log.info("Updating exercise with ID: {}", id);

        ExerciseResponse response = exerciseService.update(id, request);
        return new ResponseData<>(HttpStatus.OK.value(), "Exercise updated successfully", response);

    }

    
    // @GetMapping("/{id}")
    // @Operation(summary = "Get exercise by ID")
    // @PreAuthorize("hasAuthority('EXERCISE:DELETE:ALL')")
    // public ResponseData<ExerciseResponse> getExerciseById(@PathVariable Long id) {
        //     log.info("Getting exercise by ID: {}", id);
        
        //     ExerciseResponse response = exerciseService.findById(id);
        //     return new ResponseData<>(HttpStatus.OK.value(), "Exercise found", response);
        
        // }
        

    // All roles can view the list of exercises
    @PreAuthorize("hasAuthority('EXERCISE:READ:ALL')")
    @GetMapping
    @Operation(summary = "Get all exercises")
    public ResponseData<ExercisePageResponse> getAllExercises(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "ASC") String direction,
    @RequestParam(defaultValue = "false") boolean ignoreCase) {
        
        log.info("Getting all exercises");
        
        ExercisePageResponse responses = exerciseService.findAll(page, size, sortBy, direction, ignoreCase);
        return new ResponseData<>(HttpStatus.OK.value(), "Exercises retrieved successfully", responses);
        
    }

    // Only owner and manager can delete exercises
    @PreAuthorize("hasAuthority('EXERCISE:DELETE:ALL')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete exercise")
    public ResponseData<Void> deleteExercise(@PathVariable Long id) {
        log.info("Deleting exercise with ID: {}", id);

        exerciseService.deleteById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Exercise deleted successfully");

    }
    }
    