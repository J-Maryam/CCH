package com.cch.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
public class Cyclist implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String fName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String lName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nationality;

    @NotBlank
    @Size(min = 2, max = 50)
    private String team;

    @NotNull
    @Size(min = 2, max = 50)
    private int age;

    public Cyclist() {}

    public Cyclist(String fName, String lName, String nationality, String team, int age) {
        this.fName = fName;
        this.lName = lName;
        this.nationality = nationality;
        this.team = team;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
