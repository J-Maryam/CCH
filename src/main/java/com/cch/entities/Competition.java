package com.cch.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Competition {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "Le nom de la compétition ne doit pas être vide")
    private String name;

    @NotNull(message = "La date de début ne doit pas être vide")
    private LocalDate startDate;

    @NotNull(message = "La date de fin ne doit pas être vide")
    private LocalDate endDate;

    @NotBlank(message = "La localisation ne doit pas être vide")
    private String location;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stage> stages = new HashSet<>();

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralResult> generalResults = new HashSet<>();

    public Competition(String name, String location, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }
}
