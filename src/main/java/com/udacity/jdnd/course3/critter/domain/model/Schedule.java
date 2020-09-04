package com.udacity.jdnd.course3.critter.domain.model;

import com.google.common.base.Objects;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SCHEDULE")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection()
    private Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public Schedule(Long id, List<Employee> employees, List<Pet> pets, LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employees = employees;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equal(getId(), schedule.getId()) &&
                Objects.equal(getEmployees(), schedule.getEmployees()) &&
                Objects.equal(getPets(), schedule.getPets()) &&
                Objects.equal(getDate(), schedule.getDate()) &&
                Objects.equal(getActivities(), schedule.getActivities());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getEmployees(), getPets(), getDate(), getActivities());
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", employees=" + employees +
                ", pets=" + pets +
                ", date=" + date +
                ", activities=" + activities +
                '}';
    }
}
