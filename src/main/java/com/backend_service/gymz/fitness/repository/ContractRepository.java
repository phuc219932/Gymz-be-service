package com.backend_service.gymz.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend_service.gymz.fitness.model.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    List<ContractEntity> findByCustomer_IdOrPt_Id(Long customerId, Long ptId);

}
