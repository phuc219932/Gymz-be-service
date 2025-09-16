package com.backend_service.gymz.fitness.dto.request.equipment;

import java.util.Date;

import com.backend_service.gymz.fitness.common.enums.equipment.EquipmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentCreateRequest {

    @NotBlank(message = "name must not be blank")
    private String name;
    
    @NotBlank(message = "type must not be blank")
    private String type;
    
    @NotBlank(message = "brand must not be blank")
    private String brand;

    @NotNull(message = "purchaseDate must not be null")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    
    @NotNull(message = "Status is required")
    private EquipmentStatus status;    

    @Column(name = "description", length = 500)
    private String description;
}
