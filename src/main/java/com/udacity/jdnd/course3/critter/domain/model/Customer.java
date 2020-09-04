package com.udacity.jdnd.course3.critter.domain.model;

import com.google.common.base.Objects;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Nationalized
    @Column(nullable = false, length = 255)
    private String name;

    private String phoneNumber;
    private String notes;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber, String notes, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equal(getId(), customer.getId()) &&
                Objects.equal(getName(), customer.getName()) &&
                Objects.equal(getPhoneNumber(), customer.getPhoneNumber()) &&
                Objects.equal(getNotes(), customer.getNotes()) &&
                Objects.equal(getPets(), customer.getPets());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getPhoneNumber(), getNotes(), getPets());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", pets=" + pets +
                '}';
    }
}
