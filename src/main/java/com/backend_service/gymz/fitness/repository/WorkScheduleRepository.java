package com.backend_service.gymz.fitness.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend_service.gymz.fitness.common.enums.schedule.WorkScheduleShift;
import com.backend_service.gymz.fitness.model.WorkScheduleEntity;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkScheduleEntity, Long> {

    List<WorkScheduleEntity> findByEmployeeId(Long employeeId);

  
    List<WorkScheduleEntity> findByWorkDate(Date workDate);

   
    List<WorkScheduleEntity> findByShift(WorkScheduleShift shift);

  
    List<WorkScheduleEntity> findByEmployeeIdAndWorkDate(Long employeeId, Date workDate);

  
    List<WorkScheduleEntity> findByEmployeeIdAndShift(Long employeeId, WorkScheduleShift shift);

    List<WorkScheduleEntity> findByEmployee_Id(Long id);

   
    @Query("SELECT ws FROM WorkScheduleEntity ws WHERE ws.workDate BETWEEN :startDate AND :endDate ORDER BY ws.workDate ASC")
    List<WorkScheduleEntity> findByWorkDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

   
    @Query("SELECT ws FROM WorkScheduleEntity ws WHERE ws.employee.id = :employeeId AND ws.workDate BETWEEN :startDate AND :endDate ORDER BY ws.workDate ASC")
    List<WorkScheduleEntity> findByEmployeeIdAndWorkDateBetween(@Param("employeeId") Long employeeId, 
                                                         @Param("startDate") Date startDate, 
                                                         @Param("endDate") Date endDate);

    @Query("SELECT ws FROM WorkScheduleEntity ws LEFT JOIN FETCH ws.employee WHERE ws.id = :id")
    WorkScheduleEntity findByIdWithEmployee(@Param("id") Long id);

  
    @Query("SELECT ws FROM WorkScheduleEntity ws LEFT JOIN FETCH ws.employee ORDER BY ws.workDate DESC")
    List<WorkScheduleEntity> findAllWithEmployee();


    boolean existsByEmployeeIdAndWorkDate(Long employeeId, Date workDate);

    
    @Query("SELECT COUNT(ws) FROM WorkScheduleEntity ws WHERE ws.employee.id = :employeeId AND MONTH(ws.workDate) = :month AND YEAR(ws.workDate) = :year")
    Long countByEmployeeIdAndMonth(@Param("employeeId") Long employeeId, 
                                   @Param("month") int month, 
                                   @Param("year") int year);
}
