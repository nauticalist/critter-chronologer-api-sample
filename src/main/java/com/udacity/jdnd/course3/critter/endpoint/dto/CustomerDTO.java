package com.udacity.jdnd.course3.critter.endpoint.dto;

import com.google.common.base.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
public class CustomerDTO {
    private long id;

    @NotEmpty(message = "Name cannot be null or empty")
    @NotNull
    private String name;

    private String phoneNumber;

    private String notes;

        private List<Long> petIds;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return getId() == that.getId() &&
                Objects.equal(getName(), that.getName()) &&
                Objects.equal(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equal(getNotes(), that.getNotes()) &&
                Objects.equal(getPetIds(), that.getPetIds());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getPhoneNumber(), getNotes(), getPetIds());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", petIds=" + petIds +
                '}';
    }
}
