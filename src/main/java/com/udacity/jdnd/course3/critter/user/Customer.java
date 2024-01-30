package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(length = 25)
    private String phoneNumber;

    @Type(type = "text")
    private String notes;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    public Customer() {}

    public Customer(String phoneNumber, String notes) {
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Customer(String name, String phoneNumber, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Customer(String name, String phoneNumber, String notes, List<Pet> pets) {
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
}
