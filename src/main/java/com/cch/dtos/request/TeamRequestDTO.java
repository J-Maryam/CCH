package com.cch.dtos.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeamRequestDTO(
        @NotBlank(message = "Le nom de l'équipe ne doit pas être vide")
        @Column(unique = true)
        @Size(min = 2, max = 50) String team
) {}

