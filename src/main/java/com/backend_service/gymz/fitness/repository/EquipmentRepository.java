package com.backend_service.gymz.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend_service.gymz.fitness.common.enums.equipment.EquipmentStatus;
import com.backend_service.gymz.fitness.model.EquipmentEntity;

import java.util.List;


@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long>{
    List<EquipmentEntity> findByType(String type);
    List<EquipmentEntity> findByStatus(EquipmentStatus status);
}
