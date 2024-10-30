package com.cch.dtos.response;

import java.time.LocalDate;
import java.util.Set;

public record CompetitionDTO(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String location
) {}


