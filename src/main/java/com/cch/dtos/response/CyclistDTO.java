package com.cch.dtos.response;

import java.time.LocalDate;

public record CyclistDTO(
        Long id,
        String fName,
        String lName,
        String nationality,
        LocalDate birthDate
) {}

