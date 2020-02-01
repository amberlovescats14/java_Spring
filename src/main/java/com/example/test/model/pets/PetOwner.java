package com.example.test.model.pets;

import javax.persistence.*;

@Entity
public class PetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "varchar(30) not null ")
    private String name;

    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @OneToOne(mappedBy = "petOwner")
    private Vet vet;

    public PetOwner() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }
}