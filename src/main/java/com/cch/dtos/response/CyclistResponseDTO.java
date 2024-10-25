package com.cch.dtos.response;

import java.time.LocalDate;

public record CyclistResponseDTO(
        Long id,
        String fName,
        String lName,
        String nationality,
        LocalDate birthDate,
        TeamResponseDTO team
) {}

