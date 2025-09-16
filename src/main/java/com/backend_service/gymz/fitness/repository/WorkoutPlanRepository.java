package com.backend_service.gymz.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend_service.gymz.fitness.model.WorkoutPlanEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity, Long> {
    
    @Query("SELECT wp FROM WorkoutPlanEntity wp " +
           "LEFT JOIN FETCH wp.programExercises pe " +
           "LEFT JOIN FETCH pe.exercise e " +
           "LEFT JOIN FETCH wp.customer c " +
           "WHERE wp.id = :id")
    Optional<WorkoutPlanEntity> findByIdWithExercises(@Param("id") Long id);
    
    @Query("SELECT DISTINCT wp FROM WorkoutPlanEntity wp " +
           "LEFT JOIN FETCH wp.programExercises pe " +
           "LEFT JOIN FETCH pe.exercise e " +
           "LEFT JOIN FETCH wp.customer c")
    java.util.List<WorkoutPlanEntity> findAllWithExercises();

    List<WorkoutPlanEntity> findByCustomer_Id(Long id);
}