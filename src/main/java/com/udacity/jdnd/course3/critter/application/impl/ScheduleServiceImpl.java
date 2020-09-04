package com.udacity.jdnd.course3.critter.application.impl;

import com.udacity.jdnd.course3.critter.application.ScheduleService;
import com.udacity.jdnd.course3.critter.domain.model.Employee;
import com.udacity.jdnd.course3.critter.domain.model.Pet;
import com.udacity.jdnd.course3.critter.domain.model.Schedule;
import com.udacity.jdnd.course3.critter.domain.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.domain.repository.PetRepository;
import com.udacity.jdnd.course3.critter.domain.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    @Transactional
    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> getSchedulesForPet(long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> getScheduleForCustomer(long customerId) {
        return scheduleRepository.findAllByPetsOwnerId(customerId);
    }
}
