package com.cch.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CyclistRequestDTO(
        @NotBlank(message = "Le prénom ne doit pas être vide")
        @Size(min = 3, max = 50) String fName,

        @NotBlank(message = "Le nom ne doit pas être vide")
        @Size(min = 3, max = 50) String lName,

        @NotBlank(message = "La nationalité ne doit pas être vide")
        @Size(min = 3, max = 50) String nationality,

        @NotNull(message = "La date de naissance ne doit pas être nulle")
        @Past(message = "La date de naissance doit être dans le passé") LocalDate birthDate,

        @NotNull(message = "L'équipe ne doit pas être nulle") Long teamId
) {}