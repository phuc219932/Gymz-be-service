package com.backend_service.gymz.fitness.service;

import com.backend_service.gymz.fitness.dto.request.membership.MembershipRecordCreateRequest;
import com.backend_service.gymz.fitness.dto.request.membership.MembershipRecordUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.membership.MembershipRecordPageResponse;
import com.backend_service.gymz.fitness.dto.response.membership.MembershipRecordResponse;

public interface MembershipRecordService {
    MembershipRecordResponse save(MembershipRecordCreateRequest request);

    MembershipRecordResponse findById(Long id);

    MembershipRecordPageResponse findAll(int page, int size, String sortBy, String direction, boolean ignoreCase);

    MembershipRecordResponse update(MembershipRecordUpdateRequest request);

    void deleteById(Long id);
    
}