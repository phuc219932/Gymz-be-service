package com.backend_service.gymz.fitness.service;

import com.backend_service.gymz.fitness.dto.request.membership.MembershipCreateRequest;
import com.backend_service.gymz.fitness.dto.request.membership.MembershipUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.membership.MembershipPageResponse;
import com.backend_service.gymz.fitness.dto.response.membership.MembershipResponse;

public interface MembershipService {
    
    MembershipResponse save(MembershipCreateRequest request);
    
    MembershipResponse findById(Long id);
    
    MembershipPageResponse findAll(int page, int size, String sortBy, String direction, boolean ignoreCase);
    
    MembershipResponse update(MembershipUpdateRequest request);
    
    void deleteById(Long id);
    
}
