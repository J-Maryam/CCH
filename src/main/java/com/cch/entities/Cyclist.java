package com.cch.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
public class Cyclist implements Serializable {

    @Getter
    @Setter

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotNull
    @Size(min = 2, max = 50)
    private int age;

    public Cyclist() {
    }

    public Cyclist(String fName, String lName, String nationality, int age) {
        this.fName = fName;
        this.lName = lName;
        this.nationality = nationality;
        this.age = age;
    }

}
