package com.backend_service.gymz.fitness.model;


import com.backend_service.gymz.common.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tbl_workout_plan_exercise")
public class WorkoutPlanExercise extends AbstractEntity<Long> {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private WorkoutPlanEntity program;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;
    
    @Column(name = "sets", nullable = false)
    private int sets;
    
    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight")
    private int weight;

    @Column(name = "rest_time")
    private Integer restTime;
    
    @Column(name = "order_index")
    private Integer orderIndex;
    
    @Column(name = "description")
    private String description; 
}
