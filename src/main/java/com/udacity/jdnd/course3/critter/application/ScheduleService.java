package com.udacity.jdnd.course3.critter.application;

import com.udacity.jdnd.course3.critter.domain.model.Pet;
import com.udacity.jdnd.course3.critter.domain.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAll();

    Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds);

    List<Schedule> getSchedulesForPet(long petId);

    List<Schedule> getScheduleForEmployee(long employeeId);

    List<Schedule> getScheduleForCustomer(long customerId);
}
