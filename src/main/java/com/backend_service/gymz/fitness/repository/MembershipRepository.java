package com.backend_service.gymz.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend_service.gymz.fitness.model.MembershipEntity;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
	boolean existsByName(String name);
	boolean existsByNameAndIdNot(String name, Long id);
}
