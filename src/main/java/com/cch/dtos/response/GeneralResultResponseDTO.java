package com.cch.dtos.cyclist.response;

import java.time.Duration;

public record GeneralResultResponseDTO(
        CyclistResponseDTO cyclist,
        CompetitionResponseDTO competition,
        Duration generalTime,
        Integer generalRank
) {}



