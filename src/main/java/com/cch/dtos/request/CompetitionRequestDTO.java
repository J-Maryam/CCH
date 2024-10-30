package com.cch.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CompetitionRequestDTO(
        @NotBlank(message = "Le nom de la compétition ne doit pas être vide") String name,
        @NotNull(message = "La date de début ne doit pas être vide") LocalDate startDate,
        @NotNull(message = "La date de fin ne doit pas être vide") LocalDate endDate,
        @NotBlank(message = "La localisation ne doit pas être vide") String location
) {}
