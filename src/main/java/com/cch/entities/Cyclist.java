package com.cch.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cyclist {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message="le prenom ne doit pas etre vide")
    @Size(min = 3, max = 50)
    private String fName;

    @NotBlank(message="le nom ne doit pas etre vide")
    @Size(min = 3, max = 50)
    private String lName;

    @NotBlank(message="la nationnalite ne doit pas etre vide")
    @Size(min = 3, max = 50)
    private String nationality;

    @NotNull(message = "la date de naissance ne doit etre null")
    @Past(message = "La date doit etre dans le passe")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StageResult> stageResults = new HashSet<>();

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralResult> generalResults = new HashSet<>();

    public Cyclist(String fName, String lName, String nationality, LocalDate birthDate, Team team) {
        this.fName = fName;
        this.lName = lName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.team = team;
    }

}