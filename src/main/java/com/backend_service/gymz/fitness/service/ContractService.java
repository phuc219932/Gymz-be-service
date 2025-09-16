package com.backend_service.gymz.fitness.service;

import java.util.List;

import com.backend_service.gymz.fitness.dto.request.contract.ContractCreateRequest;
import com.backend_service.gymz.fitness.dto.response.contract.ContractPageResponse;
import com.backend_service.gymz.fitness.dto.response.contract.ContractResponse;

public interface ContractService {
    ContractResponse save(ContractCreateRequest request);

    ContractResponse findById(Long id);

    List<ContractResponse> findContractsByUserId(Long userId);

    ContractPageResponse findAll(int page, int size, String sortBy, String direction, boolean ignoreCase);

    ContractResponse update(Long id, ContractCreateRequest request);

    void deleteById(Long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);


    
}
