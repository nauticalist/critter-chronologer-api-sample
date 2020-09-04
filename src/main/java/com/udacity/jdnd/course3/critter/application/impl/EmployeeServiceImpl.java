package com.udacity.jdnd.course3.critter.application.impl;

import com.udacity.jdnd.course3.critter.application.EmployeeService;
import com.udacity.jdnd.course3.critter.domain.model.Employee;
import com.udacity.jdnd.course3.critter.domain.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.domain.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.infrastructure.exception.handler.ResourceNotFoundException;
import org.assertj.core.api.LocalDateAssert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(long id) throws ResourceNotFoundException{
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAvailableEmployees(Set<EmployeeSkill> skills, LocalDate date) {
        return employeeRepository.findEmployeesByDaysAvailableContains(date.getDayOfWeek())
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

}
