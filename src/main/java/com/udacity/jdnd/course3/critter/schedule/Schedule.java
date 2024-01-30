package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToMany
    @JoinTable(name = "employee_schedule",
    joinColumns = {@JoinColumn(name = "schedule_id", nullable = false)},
    inverseJoinColumns = {@JoinColumn(name = "employee_id", nullable = false)})

    private List<Employee> employees = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "pet_schedule",
    joinColumns = {@JoinColumn(name = "schedule_id", nullable = false)},
    inverseJoinColumns = {@JoinColumn(name = "pet_id", nullable = false)})

    private List<Pet> pets = new ArrayList<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities = new HashSet<>();

    public Schedule() {}

    public Schedule(Long id, LocalDate date, LocalTime startTime, LocalTime endTime, List<Employee> employees, List<Pet> pets, Set<EmployeeSkill> activities) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employees = employees;
        this.pets = pets;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
