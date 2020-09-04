package com.udacity.jdnd.course3.critter.endpoint.dto;

import com.udacity.jdnd.course3.critter.domain.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.domain.model.PetType;
import com.udacity.jdnd.course3.critter.infrastructure.validation.IEnumValidator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
public class PetDTO {
    private long id;
    @IEnumValidator(
            enumClazz = PetType.class,
            message = "Invalid pet type provided"
    )
    @NotEmpty(message = "Pet type cannot be null or empty")
    @NotNull
    private PetType type;

    @NotEmpty(message = "Name cannot be null or empty")
    @NotNull
    private String name;

    @NotEmpty(message = "Owner cannot be null or empty")
    @NotNull
    private long ownerId;

    @PastOrPresent(message = "You must be kidding, he/she is not born yet!!!")
    private LocalDate birthDate;

    private String notes;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
