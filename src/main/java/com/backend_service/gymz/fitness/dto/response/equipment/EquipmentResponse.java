package com.backend_service.gymz.fitness.dto.response.equipment;

import java.util.Date;

import com.backend_service.gymz.fitness.common.enums.equipment.EquipmentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentResponse {
    private Long id;
    private String name;  
    private String type;  
    private String brand;
    private Date purchaseDate; 
    private EquipmentStatus status; 
    private String description;
}
