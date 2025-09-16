package com.backend_service.gymz.fitness.service;

import java.util.List;

import com.backend_service.gymz.fitness.common.enums.equipment.EquipmentStatus;
import com.backend_service.gymz.fitness.dto.request.equipment.EquipmentCreateRequest;
import com.backend_service.gymz.fitness.dto.request.equipment.EquipmentUpdateRequest;
import com.backend_service.gymz.fitness.dto.response.equipment.EquipmentPageReponse;
import com.backend_service.gymz.fitness.dto.response.equipment.EquipmentResponse;
public interface EquipmentService {
    
    EquipmentResponse createEquipment(EquipmentCreateRequest request);
    
    EquipmentResponse updateEquipment(Long id, EquipmentUpdateRequest request);
    
    EquipmentResponse getEquipmentById(Long id);
    
    EquipmentPageReponse getAllEquipment(int page, int size, String sortBy, String direction, boolean ignoreCase);
    
    void deleteById(Long id);

    List<EquipmentResponse> getEquipmentByStatus(EquipmentStatus status);
    
    List<EquipmentResponse> getEquipmentByType(String type);
}
