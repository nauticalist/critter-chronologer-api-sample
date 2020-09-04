package com.udacity.jdnd.course3.critter.endpoint.dto;

import com.udacity.jdnd.course3.critter.domain.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.infrastructure.validation.IEnumValidator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequestDTO {
    @IEnumValidator(
            enumClazz = EmployeeSkill.class,
            message = "Invalid employee skill provided"
    )
    @NotEmpty(message = "Employee skills cannot be null or empty")
    @NotNull
    private Set<EmployeeSkill> skills;

    private LocalDate date;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
