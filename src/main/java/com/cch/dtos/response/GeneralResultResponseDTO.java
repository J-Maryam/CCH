package com.cch.dtos.response;

import java.time.Duration;

public record GeneralResultResponseDTO(
        CyclistResponseDTO cyclist,
        CompetitionResponseDTO competition,
        Duration generalTime,
        Integer generalRank
) {}