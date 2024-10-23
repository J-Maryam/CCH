package com.cch.dtos.cyclist.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CompetitionRequestDTO(
        @NotBlank(message = "Le nom de la compétition ne doit pas être vide") String name,
        @NotBlank(message = "La date de début ne doit pas être vide") LocalDate startDate,
        @NotBlank(message = "La date de fin ne doit pas être vide") LocalDate endDate,
        @NotBlank(message = "La localisation ne doit pas être vide") String location
) {}
