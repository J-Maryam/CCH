package com.cch.dtos.cyclist.response;

import java.time.LocalDate;
import java.util.Set;

public record CompetitionResponseDTO(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String location,
        Set<StageResponseDTO> stages
) {}


