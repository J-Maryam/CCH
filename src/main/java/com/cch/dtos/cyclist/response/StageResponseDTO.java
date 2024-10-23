package com.cch.dtos.cyclist.response;

import com.cch.entities.enums.StageType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record StageResponseDTO(
        Long id,
        Integer number,
        String startLocation,
        String endLocation,
        LocalDate date,
        LocalTime startTime,
        StageType stageType,
        CompetitionResponseDTO competition,
        Set<StageResultResponseDTO> results
) {}


