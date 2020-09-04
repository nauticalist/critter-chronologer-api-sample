package com.udacity.jdnd.course3.critter.endpoint.dto;

import com.udacity.jdnd.course3.critter.domain.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.infrastructure.validation.IEnumValidator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
public class EmployeeDTO {
    private long id;

    @NotEmpty(message = "Name cannot be null or empty")
    @NotNull
    private String name;

    @IEnumValidator(
            enumClazz = EmployeeSkill.class,
            message = "Invalid employee skill provided"
    )
    @NotEmpty(message = "Employee skills cannot be null or empty")
    @NotNull
    private Set<EmployeeSkill> skills;

    private Set<DayOfWeek> daysAvailable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
