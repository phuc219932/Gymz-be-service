package com.backend_service.gymz.fitness.dto.request.equipment;

import java.util.Date;

import com.backend_service.gymz.fitness.common.enums.equipment.EquipmentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentUpdateRequest {
    private String name;
    private String type;
    private String brand;
    private Date purchaseDate;
    private EquipmentStatus status;
    private String description;
}
