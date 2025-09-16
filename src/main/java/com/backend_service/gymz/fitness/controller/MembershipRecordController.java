package com.backend_service.gymz.fitness.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.backend_service.gymz.common.model.ResponseData;
import com.backend_service.gymz.fitness.dto.request.membership.MembershipRecordCreateRequest;
import com.backend_service.gymz.fitness.dto.request.membership.MembershipRecordUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.membership.MembershipRecordPageResponse;
import com.backend_service.gymz.fitness.dto.response.membership.MembershipRecordResponse;
import com.backend_service.gymz.fitness.service.MembershipRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/membership-record")
@Tag(name = "Membership Record Controller")
@RequiredArgsConstructor
@Slf4j(topic = "MEMBERSHIP-RECORD-CONTROLLER")
public class MembershipRecordController {
    private final MembershipRecordService membershipRecordService;

    @PostMapping
    // Only receptionist and owner can create membership records
    @PreAuthorize("hasAuthority('MEMBERSHIP_RECORD:CREATE:ALL')")
    @Operation(summary = "Create new membership record")
    public ResponseData<MembershipRecordResponse> createMembershipRecord(@Valid @RequestBody MembershipRecordCreateRequest req) {
        log.info("Creating membership record: {}", req);
        MembershipRecordResponse data = membershipRecordService.save(req);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Membership record created successfully", data);
    }

    @GetMapping
    // Only owner and receptionist can view all membership records
    @PreAuthorize("hasAuthority('MEMBERSHIP_RECORD:READ:ALL')")
    @Operation(summary = "Get list of all membership records")
    public ResponseData<MembershipRecordPageResponse> getAllMembershipRecords(
         @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "ASC") String direction,
        @RequestParam(defaultValue = "false") boolean ignoreCase
    ) {
        log.info("Fetching all membership records");
        MembershipRecordPageResponse data = membershipRecordService.findAll(page, size, sortBy, direction, ignoreCase);
        return new ResponseData<>(HttpStatus.OK.value(), "Membership records retrieved successfully", data);
    }

    @GetMapping("/{id}")
    // Only owner and receptionist can view membership record by ID
    @PreAuthorize("hasAuthority('MEMBERSHIP_RECORD:READ:ALL')")
    @Operation(summary = "Get membership record by ID")
    public ResponseData<MembershipRecordResponse> getMembershipRecordById(@PathVariable Long id) {
        log.info("Fetching membership record by ID: {}", id);
        MembershipRecordResponse data = membershipRecordService.findById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Membership record retrieved successfully", data);
    }

    @PutMapping("/{id}")
    // Only owner and receptionist can update membership records
    @PreAuthorize("hasAuthority('MEMBERSHIP_RECORD:UPDATE:ALL')")
    @Operation(summary = "Update membership record by ID")
    public ResponseData<MembershipRecordResponse> updateMembershipRecord(
            @PathVariable Long id,
            @Valid @RequestBody MembershipRecordUpdateRequest req) {
        log.info("Updating membership record with ID: {}", id);
        req.setId(id);
        MembershipRecordResponse data = membershipRecordService.update(req);
        return new ResponseData<>(HttpStatus.OK.value(), "Membership record updated successfully", data);
    }

    @DeleteMapping("/{id}")
    // Only owner and receptionist can delete membership records
    @PreAuthorize("hasAuthority('MEMBERSHIP_RECORD:DELETE:ALL')")
    @Operation(summary = "Delete membership record by ID")
    public ResponseData<Void> cancelMembershipRecord(@PathVariable Long id) {
        log.info("Cancelling membership record with ID: {}", id);
        membershipRecordService.deleteById(id);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Membership record cancelled successfully");
    }
}
