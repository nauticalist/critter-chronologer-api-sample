package com.udacity.jdnd.course3.critter.domain.repository;

import com.udacity.jdnd.course3.critter.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByEmployeesId(long employeeId);

    List<Schedule> findAllByPetsId(long petId);

    List<Schedule> findAllByPetsOwnerId(long customerId);
}
