package com.backend_service.gymz.fitness.model;

import java.util.ArrayList;
import java.util.List;

import com.backend_service.gymz.common.model.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import lombok.Getter;
import lombok.Setter;

//  Exercise ↔ WorkoutProgram: Many-to-Many

@Getter
@Setter
@Entity
@Table(name = "tbl_exercise")
public class ExerciseEntity extends AbstractEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<WorkoutPlanExercise> programExercises = new ArrayList<>();
}