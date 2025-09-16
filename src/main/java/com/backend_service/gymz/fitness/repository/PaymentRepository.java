package com.backend_service.gymz.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend_service.gymz.fitness.model.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    boolean existsByMembershipId(Long membershipId);
    boolean existsByContractId(Long contractId);
    // List<PaymentEntity> findByCustomerId(Long customerId);
    List<PaymentEntity> findByMembershipId(Long membershipId);
    List<PaymentEntity> findByContractId(Long contractId);
    List<PaymentEntity> findByMembership_Customer_Id(Long id);
    List<PaymentEntity> findByContract_Customer_Id(Long id);
    List<PaymentEntity> findByMembership_Customer_IdOrContract_Customer_Id(Long membership_id, Long contract_id);
}
