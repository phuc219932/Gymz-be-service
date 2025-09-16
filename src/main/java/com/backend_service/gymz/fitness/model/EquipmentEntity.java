package com.backend_service.gymz.fitness.model;

import java.util.Date;

import com.backend_service.gymz.common.model.AbstractEntity;
import com.backend_service.gymz.fitness.common.enums.equipment.EquipmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_equipment")
public class EquipmentEntity extends AbstractEntity<Long> {
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EquipmentStatus status;
    
    @Column(name = "description")
    private String description;
 
}
