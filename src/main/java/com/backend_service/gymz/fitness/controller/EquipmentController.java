package com.backend_service.gymz.fitness.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend_service.gymz.common.model.ResponseData;
import com.backend_service.gymz.fitness.dto.request.equipment.EquipmentCreateRequest;
import com.backend_service.gymz.fitness.dto.request.equipment.EquipmentUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.equipment.EquipmentPageReponse;
import com.backend_service.gymz.fitness.dto.response.equipment.EquipmentResponse;
import com.backend_service.gymz.fitness.service.EquipmentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/equipment")
@Tag(name = "Equipment Controller")
@RequiredArgsConstructor
@Slf4j(topic = "EQUIPMENT-CONTROLLER")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping
    @PreAuthorize("hasAuthority('EQUIPMENT:CREATE:ALL')")
    @Operation(summary = "Create new equipment")
    public ResponseData<EquipmentResponse> createEquipment(@Valid @RequestBody EquipmentCreateRequest req) {
        log.info("Creating equipment: {}", req);

        EquipmentResponse data = equipmentService.createEquipment(req);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Equipment created successfully", data);

    }

    @GetMapping
    @PreAuthorize("hasAuthority('EQUIPMENT:READ:ALL')")
    @Operation(summary = "Get list of all equipment")
    public ResponseData<EquipmentPageReponse> getListEquipment(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "ASC") String direction,
        @RequestParam(defaultValue = "false") boolean ignoreCase) {

        log.info("Fetching all equipment");

        EquipmentPageReponse data = equipmentService.getAllEquipment(page, size, sortBy, direction, ignoreCase);
        return new ResponseData<>(HttpStatus.OK.value(), "Equipment list retrieved successfully", data);

    }

    // @GetMapping("/{id}")
    // @Operation(summary = "Get equipment by ID")
    // public ResponseData<EquipmentResponse> getEquipmentById(@PathVariable Long id) {
    //     log.info("Fetching equipment by ID: {}", id);

    //     EquipmentResponse data = equipmentService.getEquipmentById(id);
    //     return new ResponseData<>(HttpStatus.OK.value(), "Equipment retrieved successfully", data);

    // }

    @PutMapping("/api/v1/{id}")
    @PreAuthorize("hasAuthority('EQUIPMENT:UPDATE:ALL')")
    @Operation(summary = "Update equipment by ID")
    public ResponseData<EquipmentResponse> updateEquipment(@PathVariable Long id,
            @Valid @RequestBody EquipmentUpdateRequest req) {
        log.info("Updating equipment with ID: {}", id);

        EquipmentResponse data = equipmentService.updateEquipment(id, req);
        return new ResponseData<>(HttpStatus.OK.value(), "Equipment updated successfully", data);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete equipment by ID")
    @PreAuthorize("hasAuthority('EQUIPMENT:DELETE:ALL')")
    public ResponseData<Void> deleteEquipment(@PathVariable Long id) {
        log.info("Deleting equipment with ID: {}", id);

        equipmentService.deleteById(id);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Equipment deleted successfully");

    }

}
