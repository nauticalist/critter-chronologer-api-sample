package com.udacity.jdnd.course3.critter.domain.model;

import com.google.common.base.Objects;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PETS")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PetType type;

    @Nationalized
    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer owner;

    private LocalDate birthDate;

    private String notes;

    public Pet() {
    }

    public Pet(Long id, PetType type, String name, Customer owner, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equal(getId(), pet.getId()) &&
                getType() == pet.getType() &&
                Objects.equal(getName(), pet.getName()) &&
                Objects.equal(getOwner(), pet.getOwner()) &&
                Objects.equal(getBirthDate(), pet.getBirthDate()) &&
                Objects.equal(getNotes(), pet.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getType(), getName(), getOwner(), getBirthDate(), getNotes());
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}
