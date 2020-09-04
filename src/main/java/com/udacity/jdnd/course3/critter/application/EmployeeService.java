package com.udacity.jdnd.course3.critter.application;

import com.udacity.jdnd.course3.critter.domain.model.Employee;
import com.udacity.jdnd.course3.critter.domain.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.infrastructure.exception.handler.ResourceNotFoundException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(long id) throws ResourceNotFoundException;

    List<Employee> getAvailableEmployees(Set<EmployeeSkill> skills, LocalDate date);
}
